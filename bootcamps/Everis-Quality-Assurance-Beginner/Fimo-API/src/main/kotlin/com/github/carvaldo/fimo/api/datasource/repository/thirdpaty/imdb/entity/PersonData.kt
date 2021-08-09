package com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity

import java.util.Date

data class PersonData(
    val summary: String? = null,
    val image: String? = null,
    val role: String? = null,
    val knownFor: List<KnownForItem>? = null,
    val castMovies: List<CastMoviesItem>? = null,
    val awards: String? = null,
    val name: String? = null,
    val deathDate: Date? = null,
    val errorMessage: String? = null,
    val id: String? = null,
    val birthDate: Date? = null,
    val height: String? = null
)

data class KnownForItem(
	val image: String? = null,
	val fullTitle: String? = null,
	val role: String? = null,
	val year: String? = null,
	val id: String? = null,
	val title: String? = null
)

data class CastMoviesItem(
	val role: String? = null,
	val year: String? = null,
	val description: String? = null,
	val id: String? = null,
	val title: String? = null
)