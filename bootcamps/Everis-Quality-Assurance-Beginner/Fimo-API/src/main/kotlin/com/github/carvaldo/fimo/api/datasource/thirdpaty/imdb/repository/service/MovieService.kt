package com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service

import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.MovieDetail
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("SearchMovie/{key}/{name}")
    fun search(@Path("key") key: String, @Path("name") name: String): Call<SearchResult>

    @GET("Title/{key}/{id}")
    fun detail(@Path("key") key: String, @Path("id") id: String): Call<MovieDetail>
}