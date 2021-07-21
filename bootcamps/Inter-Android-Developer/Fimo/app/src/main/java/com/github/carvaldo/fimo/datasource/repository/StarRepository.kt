package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.Star

class StarRepository(private val database: DatabaseApp) {
    private val starDao by lazy { database.getStarDao() }

    fun save(stars: List<Star>?) {
        stars?.also { starDao.save(it) }
    }
}