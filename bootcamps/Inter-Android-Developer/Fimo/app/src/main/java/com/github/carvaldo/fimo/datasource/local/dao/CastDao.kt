package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.carvaldo.fimo.datasource.local.entity.CastMovie

@Dao
interface CastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cast: CastMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg cast: CastMovie): List<Long>

    @Query("SELECT * FROM cast_movie WHERE apiId LIKE :id")
    fun findByApiId(id: String): CastMovie
}