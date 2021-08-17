package com.github.carvaldo.fimo.api.datasource.repository

import com.github.carvaldo.fimo.api.datasource.repository.service.case.MovieUseCase
import com.github.carvaldo.fimo.api.datasource.repository.local.dao.MovieDao
import com.github.carvaldo.fimo.api.datasource.repository.local.dao.MovieResultDao
import com.github.carvaldo.fimo.api.datasource.repository.local.entity.MovieDetail
import com.github.carvaldo.fimo.api.datasource.repository.local.entity.ResultMovie
import com.github.carvaldo.fimo.api.datasource.repository.local.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.MovieService
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieRepository constructor(
    private val movieUseCase: MovieUseCase,
    private val resultDao: MovieResultDao,
    private val movieDao: MovieDao,
    private val movieService: MovieService
){
    @Autowired
    private lateinit var logger: Logger

    fun searchByName(name: String): List<ResultMovie> {
        var movies: List<ResultMovie> = resultDao.findAllByByTitle(name)
        if (movies.isEmpty()) {
            movies = movieUseCase.searchFromImdb(name)
                .map { it.transform() }
                .let { resultDao.saveAll(it) }
                .toList()
        }
        return movies
    }

    fun getDetail(apiId: String): MovieDetail? {
        return movieDao.getMovieDetailByApiId(apiId)
            ?: movieUseCase.getDetailFromImdb(apiId)
                .transform()
                .let { movieDao.save(it) }
    }
}