package com.github.carvaldo.fimo.api.datasource.repository.local.dao

import com.github.carvaldo.fimo.api.datasource.repository.local.entity.MovieDetail
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieDao: CrudRepository<MovieDetail, Long> {
    fun getMovieDetailByApiId(apiId: String): MovieDetail?
}