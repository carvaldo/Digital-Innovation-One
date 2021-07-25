package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.carvaldo.fimo.datasource.local.entity.Director
import com.github.carvaldo.fimo.datasource.local.entity.DirectorMovie

@Dao
interface DirectorMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: DirectorMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entities: List<DirectorMovie>): List<Long>

    @Query("SELECT d.* FROM director d INNER JOIN director_movie dm ON dm.directorId = d.apiId WHERE dm.movieId = :movieRemoteId")
    fun findFromMovie(movieRemoteId: String): List<Director>
}