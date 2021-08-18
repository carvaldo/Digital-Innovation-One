package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.datasource.local.entity.SearchResultMovie
import com.github.carvaldo.fimo.datasource.local.entity.convertToLocalData
import com.github.carvaldo.fimo.datasource.local.entity.transform
import com.github.carvaldo.fimo.datasource.remote.response.MovieDetail as MovieDetailApi
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
    fun searchAsync(query: String): Data<List<SearchResultMovie>> =
        searchedRepository.findSearch(query, ResultType.TITLE).let { search ->
            return when (search != null) {
                true -> { // Consultar banco de dados local.
                    Data(searchedRepository.findHistoryFromMovie(search.id!!), null)
                }
                false -> { // Consultar API.
                    val response = service.search(query).execute()
                    when (response.isSuccessful) { // TODO: Separar em uma nova function.
                        true -> { // Analisar tipo de resposta, processar e retornar para o requisitante.
                            with(response.body()!!) {
                                return@with if (!this.error.isNullOrBlank()) { // API invalidou a solicitacao.
                                    Data<List<SearchResultMovie>>(null, this.error) // Retornar erro.
                                } else { // Converter e armazenar dados localmente.
                                    val results = this.data.map { it.convertToLocalData() }
                                    // Registrar histórico da busca.
                                    val ids = movieDao.saveResult(results)
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

    fun save(resultMovies: List<SearchResultMovie>): List<Long> = movieDao.saveResult(resultMovies)

    /**
     * Efetuaa a busca de detalhes do filme pela identificação na API. Em caso de dados inexistentes localmente, a informação é armezanada localmente.
     *
     * @param apiId
     * @return
     */
    fun findDetail(apiId: String): Data<MovieDetail> {
        val localResult = movieDao.findDetail(apiId)
        return localResult?.let { Data(it, null) } ?: loadDataFromApi(apiId)
    }

    /**
     * Analisar tipo de resposta, processar e retornar para o requisitante.
     */
    private fun loadDataFromApi(apiId: String): Data<MovieDetail> {
        return service.detail(apiId).execute().let { response ->
            when {
                // Falha na execução: 500+ // TODO: Testar casos de erro 400 e 500.
                !response.isSuccessful -> Data(null, "Ocorreu um erro. Por favor, novamente mais tarde.")
                // Solicitação recusada / problemas técnicos: -500
                !response.body()?.error.isNullOrBlank() -> Data(null, response.body()?.error)
                response.isSuccessful -> Data(saveInDatabase(response.body()!!.data), null)
                else -> Data(null, "Houve uma falha na solicitação. Por favor, verifique se há atualizações disponíveis e tente novamente.")
            }
        }
    }

    private fun saveInDatabase(entity: MovieDetailApi): MovieDetail =
        movieDao.get(movieDao.save(entity.transform()))
}