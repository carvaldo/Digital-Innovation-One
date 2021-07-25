package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "director_movie")
data class DirectorMovie(val directorId: String, val movieId: String) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
