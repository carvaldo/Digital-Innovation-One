package com.github.carvaldo.fimo.datasource.repository

import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.Searched
import com.github.carvaldo.fimo.datasource.local.SearchedResult
import com.github.carvaldo.fimo.datasource.local.dao.SearchedDao
import com.github.carvaldo.fimo.datasource.local.dao.SearchedMoviedDao

class SearchedRepository(private val searchedDao: SearchedDao, private val searchedMoviedDao: SearchedMoviedDao) {
    fun saveSearchedMovie(query: String, type: ResultType, movieIds: List<Long>) {
        val searched = Searched(query = query, type = type.name)
        searched.id = searchedDao.save(searched)[0]
        movieIds.forEach {
            searchedMoviedDao.save(SearchedResult(searchId = searched.id!!, resultId = it))
        }
    }

    fun findHistoryFromMovie(searchId: Long) = searchedMoviedDao.findBySearchBefore(searchId)

    fun findSearch(query: String, type: ResultType): Searched? = searchedDao.isSearched(query, type)
}