package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.carvaldo.fimo.datasource.local.entity.Star
import com.github.carvaldo.fimo.datasource.local.entity.StarMovie

@Dao
interface StarMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: StarMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entities: List<StarMovie>): List<Long>

    @Query("SELECT s.* FROM star s INNER JOIN star_movie sm ON sm.startId = s.apiId WHERE sm.movieId = :movieRemoteId")
    fun findFromMovie(movieRemoteId: String): List<Star>
}