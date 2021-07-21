package com.github.carvaldo.fimo.datasource.remote.response


/**
 * Movie API
 *
 * @property id
 * @property resultType
 * @property image
 * @property title
 * @property description
 */
data class Movie(
    val id: String,
    val resultType: String, // TODO: Mapear para Enum?
    val image: String,
    val title: String,
    val description: String
)
