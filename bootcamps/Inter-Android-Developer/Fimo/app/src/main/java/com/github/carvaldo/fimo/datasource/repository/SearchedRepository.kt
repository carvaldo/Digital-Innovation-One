package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.DatabaseApp
import com.github.carvaldo.fimo.datasource.local.entity.Searched
import com.github.carvaldo.fimo.datasource.local.entity.SearchedResult
import com.github.carvaldo.fimo.datasource.local.dao.SearchedDao
import com.github.carvaldo.fimo.datasource.local.dao.SearchedMoviedDao
import com.github.carvaldo.fimo.datasource.local.entity.SearchResultMovie
import javax.inject.Inject

class SearchedRepository @Inject constructor(
    private val database: DatabaseApp
) {
    private val searchedDao: SearchedDao by lazy { database.getSearchedDao() }
    private val searchedMoviedDao: SearchedMoviedDao by lazy { database.getSearchedMoviedDao() }

    fun saveSearchedMovie(query: String, type: ResultType, movieIds: List<Long>) {
        val searched = Searched(query = query, type = type.name)
        searched.id = searchedDao.save(searched)[0]
        movieIds.forEach {
            searchedMoviedDao.save(SearchedResult(searchId = searched.id!!, resultId = it))
        }
    }

    // TODO: Revisar
    fun findHistoryFromMovie(searchId: Long): List<SearchResultMovie>? = null //searchedMoviedDao.findBySearchBefore(searchId)

    fun findSearch(query: String, type: ResultType): Searched? = searchedDao.isSearched(query, type)
}