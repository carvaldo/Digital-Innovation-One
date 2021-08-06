package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.Director
import javax.inject.Inject

class DirectorRepository @Inject constructor(
    private val database: DatabaseApp
) {
    private val directorDao by lazy { database.getDirectorDao() }
    private val directorMovieDao by lazy { database.getDirectorMovieDa() }

    fun save(directors: List<Director>?) {
        directors?.also { directorDao.save(it) }
    }

    fun findFromMovie(movieRemoteId: String) = directorMovieDao.findFromMovie(movieRemoteId)
}