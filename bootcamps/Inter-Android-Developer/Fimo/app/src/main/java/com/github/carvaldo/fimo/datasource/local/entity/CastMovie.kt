package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cast_movie", indices = [Index("apiId", unique = true)])
data class CastMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val role: String? = null,
    val year: Date? = null,
    val description: String? = null,
    val title: String? = null,
    val apiId: String? = null
)