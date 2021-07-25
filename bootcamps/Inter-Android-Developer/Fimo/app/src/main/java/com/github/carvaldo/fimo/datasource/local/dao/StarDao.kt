package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.entity.Star

@Dao
interface StarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg star: Star): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(stars: List<Star>): List<Long>

    @Delete
    fun delete(vararg star: Star)

    @Query("SELECT * FROM star WHERE apiId IN (:apiId)")
    fun find(vararg apiId: String): List<Star>
}