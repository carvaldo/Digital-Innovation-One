package com.github.carvaldo.fimo.datasource.remote.response

data class SearchResultMovie(
    val remoteId: String,
    val resultType: String,
    val image: String,
    val title: String,
    val description: String
)