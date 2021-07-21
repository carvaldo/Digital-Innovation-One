package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.datasource.local.entity.ResultMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg resultMovie: ResultMovie): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg movieDetail: MovieDetail): List<Long>

    @Delete
    fun delete(resultMovie: ResultMovie)

    @Query("SELECT * FROM result WHERE title LIKE :query")
    fun search(query: String): List<ResultMovie>

    @Query("SELECT * FROM movie_detail WHERE apiId LIKE :apiId")
    fun findDetail(apiId: String): MovieDetail?
}