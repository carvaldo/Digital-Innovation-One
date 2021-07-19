package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.ResultMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg resultMovie: ResultMovie): List<Long>

    @Delete
    fun delete(resultMovie: ResultMovie)

    @Query("SELECT * FROM result WHERE title LIKE :query")
    fun search(query: String): List<ResultMovie>
}