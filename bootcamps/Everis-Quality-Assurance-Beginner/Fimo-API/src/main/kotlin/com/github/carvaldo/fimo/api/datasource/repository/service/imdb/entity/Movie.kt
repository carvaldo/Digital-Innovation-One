package com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity

data class Movie(
    val id: String,
    val resultType: String, // TODO: Mapear para Enum
    val image: String,
    val title: String,
    val description: String
)
