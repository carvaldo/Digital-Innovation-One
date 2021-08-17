package com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity

import java.util.Date

data class Person(
    val summary: String? = null,
    val image: String? = null,
    val role: String? = null,
    val awards: String? = null,
    val name: String? = null,
    val deathDate: Date? = null,
    val errorMessage: String? = null,
    val id: String? = null,
    val birthDate: Date? = null,
    val height: String? = null
)