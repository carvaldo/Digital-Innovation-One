package com.github.carvaldo.fimo.api.datasource.repository

import com.github.carvaldo.fimo.api.datasource.repository.firstparty.dao.MovieResultDao
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.ResultMovie
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.MovieService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import javax.servlet.UnavailableException

@Service
class MovieRepository constructor(private val dao: MovieResultDao, private val movieService: MovieService) {
    private val logger by lazy { LogManager.getLogger() }

    fun searchByName(name: String): List<ResultMovie> {
        var movies: List<ResultMovie> = dao.findAllByByTitle(name)
        if (movies.isEmpty()) {
            val response = movieService.search(ServiceGenerator.API_KEY, name).execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                } else {
                    movies = body.results.map { it.transform() }
                    movies = dao.saveAll(movies).toList()
                }
            } else {
                throw UnavailableException("Serviço indisponível.")
            }
        }
        return movies
    }
}