package com.github.carvaldo.fimo.datasource.remote.response

import java.util.*

data class MovieDetail(
	val id: Long,
	val apiId: String,
	val title: String? = null,
	val type: String? = null,
	val imDbRating: String? = null,
	val trailer: String? = null,
	val plotLocal: String? = null,
	val companies: String? = null,
	val plot: String? = null,
	val genres: String? = null,
	val image: String? = null,
	val fullTitle: String? = null,
	val images: Any? = null,
	val releaseDate: Date? = null,
)