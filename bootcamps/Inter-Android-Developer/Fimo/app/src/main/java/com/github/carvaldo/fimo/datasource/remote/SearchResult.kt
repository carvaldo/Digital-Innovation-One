package com.github.carvaldo.fimo.datasource.remote

data class SearchResult (
    val searchType: String,
    val expression: String,
    val results: List<Movie>,
    val errorMessage: String?
)