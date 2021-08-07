package com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb

data class SearchResult (
    val searchType: String,
    val expression: String,
    val results: List<Movie>,
    val errorMessage: String?
)