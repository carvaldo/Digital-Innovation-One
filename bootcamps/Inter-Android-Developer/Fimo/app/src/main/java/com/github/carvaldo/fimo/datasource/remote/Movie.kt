package com.github.carvaldo.fimo.datasource.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Movie(
    val id: String,
    val resultType: String, // TODO: Mapear para Sealed Class
    val image: String,
    val title: String,
    val description: String
)
