package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.Director

class DirectorRepository(private val database: DatabaseApp) {
    private val directorDao by lazy { database.getDirectorDao() }

    fun save(directors: List<Director>?) {
        directors?.also { directorDao.save(it) }
    }
}