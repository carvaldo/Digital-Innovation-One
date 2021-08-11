package com.github.carvaldo.fimo.api.datasource.repository

import com.github.carvaldo.fimo.api.datasource.repository.firstparty.dao.MovieDao
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.dao.MovieResultDao
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.MovieDetail
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.ResultMovie
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.MovieService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.UnavailableException

@Service
class MovieRepository constructor(
    private val resultDao: MovieResultDao,
    private val movieDao: MovieDao,
    private val movieService: MovieService
){
    @Autowired
    private lateinit var logger: Logger

    fun searchByName(name: String): List<ResultMovie> {
        var movies: List<ResultMovie> = resultDao.findAllByByTitle(name)
        if (movies.isEmpty()) {
            val response = movieService.search(ServiceGenerator.API_KEY, name).execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                } else {
                    movies = body.results.map { it.transform() }
                    movies = resultDao.saveAll(movies).toList()
                }
            } else {
                throw UnavailableException("Serviço indisponível.")
            }
        }
        return movies
    }

    fun getDetail(apiId: String): MovieDetail? {
        var movie: MovieDetail? = movieDao.getMovieDetailByApiId(apiId)
        if (movie == null) {
            val response = movieService.getDetail(ServiceGenerator.API_KEY, apiId).execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                } else {
                    movie = body.transform().let { movieDao.save(it) }
                }
            } else {
                throw UnavailableException("Serviço indisponível.")
            }
        }
        return movie
    }
}