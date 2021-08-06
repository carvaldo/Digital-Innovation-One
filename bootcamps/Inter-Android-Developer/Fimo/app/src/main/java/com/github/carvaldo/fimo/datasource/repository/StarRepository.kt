package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.Star
import javax.inject.Inject

class StarRepository @Inject constructor(
    private val database: DatabaseApp
) {
    private val starDao by lazy { database.getStarDao() }
    private val starMovieDao by lazy { database.getStarMovieDao() }

    fun save(stars: List<Star>?) {
        stars?.also { starDao.save(it) }
    }

    fun find(vararg apiId: String): List<Star> = starDao.find(*apiId)

    fun findFromMovie(movieRemoteId: String) = starMovieDao.findFromMovie(movieRemoteId)
}