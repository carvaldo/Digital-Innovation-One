package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.ResultMovie
import com.github.carvaldo.fimo.datasource.local.SearchedResult

@Dao
interface SearchedMoviedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg searchedResult: SearchedResult): List<Long>

    @Delete
    fun delete(searchedResult: SearchedResult)

    @Query("SELECT * FROM result WHERE id IN (SELECT sr.resultId FROM searched_result sr WHERE sr.searchId = :id)")
    fun findBySearchBefore(id: Long): List<ResultMovie>
}