package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.local.entity.Searched

@Dao
interface SearchedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg searched: Searched): List<Long>

    @Delete
    fun delete(searched: Searched)

    @Query("SELECT * FROM searched WHERE `query` LIKE :query AND type LIKE :type")
    fun isSearched(query: String, type: ResultType): Searched?
}