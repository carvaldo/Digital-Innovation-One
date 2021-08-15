package com.github.carvaldo.fimo.datasource.remote.service

import com.github.carvaldo.fimo.datasource.remote.response.MovieDetail
import com.github.carvaldo.fimo.datasource.remote.response.ResponseApi
import com.github.carvaldo.fimo.datasource.remote.response.SearchResultMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Movie service
 *
 */
interface MovieService {
    /**
     * Search movie by name.
     */
    @GET("movies/search/{query}")
    fun search(@Path("query") query: String): Call<ResponseApi<List<SearchResultMovie>>>

    /**
     * Detail for movie by id.
     */
    @GET("movies/detail/{id}")
    fun detail(@Path("id") id: String): Call<ResponseApi<MovieDetail>>
}