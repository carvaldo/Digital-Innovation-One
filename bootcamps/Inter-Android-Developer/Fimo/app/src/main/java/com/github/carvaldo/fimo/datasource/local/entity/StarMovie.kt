package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "star_movie")
data class StarMovie(val startId: String, val movieId: String) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
