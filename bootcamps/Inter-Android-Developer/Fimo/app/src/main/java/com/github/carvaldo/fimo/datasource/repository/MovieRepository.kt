package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.ResultMovie
import com.github.carvaldo.fimo.datasource.local.dao.MovieDao
import com.github.carvaldo.fimo.datasource.remote.MovieService


/**
 * Movie repository
 *
 * @property service
 * @property movieDao
 * @property searchedRepository
 */
class MovieRepository(private val service: MovieService, private val movieDao: MovieDao,
                      private val searchedRepository: SearchedRepository) {

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
                                    Data<List<ResultMovie>>(null, this.errorMessage)
                                } else { // Converter e armazenar dados localmente.
                                    val results = this.results.map {
                                        ResultMovie(
                                            remoteId = it.id,
                                            resultType = ResultType.TITLE,
                                            image = it.image,
                                            title = it.title,
                                            description = it.description
                                        )
                                    }
                                    val ids = movieDao.save(*results.toTypedArray())
                                    searchedRepository.saveSearchedMovie(query, ResultType.TITLE, ids)
                                    Data(results, null)
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
}