package com.github.carvaldo.fimo.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.carvaldo.fimo.datasource.local.entity.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(persond: Person): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg persond: Person): List<Long>

    @Query("SELECT * FROM person WHERE apiId LIKE :id")
    fun findByApiId(id: String): Person
}