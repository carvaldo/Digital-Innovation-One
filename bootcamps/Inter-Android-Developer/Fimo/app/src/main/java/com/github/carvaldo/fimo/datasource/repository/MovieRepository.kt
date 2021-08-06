package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.*
import com.github.carvaldo.fimo.datasource.remote.service.MovieService
import javax.inject.Inject

// TODO Remover dependências internas de repositories

/**
 * Movie repository
 *
 * @property service
 * @property movieDao
 * @property searchedRepository
 */
class MovieRepository @Inject constructor(
    private val database: DatabaseApp,
    private val searchedRepository: SearchedRepository,
    private val directorRepository: DirectorRepository,
    private val starRepository: StarRepository,
private val service: MovieService
) {
    private val movieDao by lazy { database.getMovieDao() }
    private val starMovieDao by lazy { database.getStarMovieDao() }
    private val directorMovieDao by lazy { database.getDirectorMovieDa() }

    /**
     * Search async: Check if search has been done before. If so, consult local data. If not, see API.
     *
     * @param query
     */
    fun searchAsync(query: String): Data<List<ResultMovie>> =
        searchedRepository.findSearch(query, ResultType.TITLE).let { search ->
            return when (search != null) {
                true -> { // Consultar banco de dados local.
                    Data(searchedRepository.findHistoryFromMovie(search.id!!), null)
                }
                false -> { // Consultar API.
                    val response = service.search(query).execute()
                    when (response.isSuccessful) {
                        true -> { // Analisar tipo de resposta, processar e retornar para o requisitante.
                            with(response.body()!!) {
                                return@with if (!this.errorMessage.isNullOrBlank()) { // API invalidou a solicitacao.
                                    Data<List<ResultMovie>>(null, this.errorMessage) // Retornar erro.
                                } else { // Converter e armazenar dados localmente.
                                    val results = this.results.map { it.transform() }
                                    // Registrar histórico da busca.
                                    val ids = movieDao.save(*results.toTypedArray())
                                    searchedRepository.saveSearchedMovie(query, ResultType.TITLE, ids)
                                    Data(results, null) // Retornar dados processados.
                                }
                            }
                        }
                        false -> { // Erro 400+
                            response.errorBody()?.let {
                                Data(null, it.string())
                            } ?: Data(null, "Ocorreu um erro inesperado.")
                        }
                    }
                }
            }
        }

    /**
     * Save observable
     *
     * @param resultMovie
     */
    fun save(vararg resultMovie: ResultMovie): List<Long> = movieDao.save(*resultMovie)

    /**
     * Efetuaa a busca de detalhes do filme pela identificação na API. Em caso de dados inexistentes localmente, a informação é armezanada localmente.
     *
     * @param apiId
     * @return
     */
    fun findDetail(apiId: String): Data<MovieDetail> = movieDao.findDetail(apiId).let { detail ->
        when (detail != null) {
            true -> handleLocalData(detail)
            false -> requestRemoteData(apiId)
        }
    }

    private fun handleLocalData(data: MovieDetail) = data.let {
        it.stars = starRepository.findFromMovie(it.apiId)
        it.directors = directorRepository.findFromMovie(it.apiId)
        Data(it, null)
    }

    /**
     * Analisar tipo de resposta, processar e retornar para o requisitante.
     *
     * @param apiId Identification from API.
     * @return [Data]<T>
     */
    private fun requestRemoteData(apiId: String): Data<MovieDetail>
            = service.detail(apiId).execute().let { response ->
        return if (response.isSuccessful) {
            val result = response.body()?.transform()
            directorRepository.save(result?.directors)
            starRepository.save(result?.stars)
            if (result != null) {
                movieDao.save(result)
                result.stars?.forEach {
                    starMovieDao.save(StarMovie(it.apiId, result.apiId))
                }
                result.directors?.forEach {
                    directorMovieDao.save(DirectorMovie(it.apiId, result.apiId))
                }
            }
            Data(result, null)
        } else { // Erro 400+
            response.errorBody()?.let {
                Data(null, it.string())
            } ?: Data(null, "Ocorreu um erro inesperado.")
        }
    }
}