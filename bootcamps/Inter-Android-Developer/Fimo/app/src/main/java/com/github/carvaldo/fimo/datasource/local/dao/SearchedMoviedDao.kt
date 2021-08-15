package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.github.carvaldo.fimo.datasource.local.entity.SearchedResult

// TODO: Revisar implementação de histórico de busca.

@Dao
interface SearchedMoviedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg searchedResult: SearchedResult): List<Long>

    @Delete
    fun delete(searchedResult: SearchedResult)
//
//    @Query("SELECT * FROM  WHERE id IN (SELECT sr.resultId FROM searched_result sr WHERE sr.searchId = :id)")
//    fun findBySearchBefore(id: Long): List<ResultMovie>
//
//    @Query("SELECT * FROM result WHERE id IN (SELECT sr.resultId FROM searched_result sr WHERE sr.searchId = :id)")
//    fun findBySearchBeforeDetail(id: Long): List<ResultMovie>
}