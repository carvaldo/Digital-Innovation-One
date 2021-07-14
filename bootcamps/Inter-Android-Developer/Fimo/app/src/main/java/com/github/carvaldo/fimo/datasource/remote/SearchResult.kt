package com.github.carvaldo.fimo.datasource.remote

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.ArrayList

//@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResult (
    val searchType: String,
    val expression: String,
    val results: List<Movie>,
    val errorMessage: String
)