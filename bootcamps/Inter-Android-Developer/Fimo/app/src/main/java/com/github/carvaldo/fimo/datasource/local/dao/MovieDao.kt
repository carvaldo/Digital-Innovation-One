package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.datasource.local.entity.SearchResultMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(resultMovie: SearchResultMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveResult(resultMovie: List<SearchResultMovie>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movieDetail: MovieDetail): Long

    @Delete
    fun delete(resultMovie: SearchResultMovie)

    @Query("SELECT * FROM search_result_movie WHERE title LIKE :query")
    fun search(query: String): List<SearchResultMovie>

    @Query("SELECT * FROM movie_detail WHERE apiId LIKE :apiId")
    fun findDetail(apiId: String): MovieDetail?

    @Query("SELECT * FROM movie_detail WHERE id LIKE :id")
    fun get(id: Long): MovieDetail
}