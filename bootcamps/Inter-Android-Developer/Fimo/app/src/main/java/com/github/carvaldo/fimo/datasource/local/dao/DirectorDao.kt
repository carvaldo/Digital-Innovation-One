package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.github.carvaldo.fimo.datasource.local.entity.Director

@Dao
interface DirectorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg director: Director): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(director: List<Director>): List<Long>

    @Delete
    fun delete(vararg director: Director)
}