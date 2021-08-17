package com.github.carvaldo.fimo.api.datasource.repository.service.case

import com.github.carvaldo.fimo.api.datasource.repository.local.dao.MovieDao
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity.MovieDetail as MovieDetailImdb
import com.github.carvaldo.fimo.api.datasource.repository.service.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity.Movie as MovieImdb
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.MovieService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.UnavailableException

@Component
class MovieUseCase(
    private val movieService: MovieService
) {
    @Autowired
    private lateinit var logger: Logger

    @Throws(UnavailableException::class)
    fun searchFromImdb(name: String): List<MovieImdb> {
        // TODO: Remover constante ServiceGenerator.API_KEY.
        return movieService.search(ServiceGenerator.API_KEY, name)
            .execute()
            .let { response ->
                val body = response.body()
                when (response.isSuccessful) {
                    true -> {
                        if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                            throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                        } else body.results
                    }
                    else -> throw UnavailableException("Serviço indisponível.")
                }
            }
    }

    fun getDetailFromImdb(apiId: String): MovieDetailImdb {
        return movieService.getDetail(ServiceGenerator.API_KEY, apiId)
            .execute()
            .let { response ->
                when (response.isSuccessful) {
                    true -> {
                        if (response.body() == null || !response.body()?.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                            throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                        } else {
                            response.body()!!
                        }
                    }
                    else -> throw UnavailableException("Serviço indisponível.")
                }
            }
    }
}