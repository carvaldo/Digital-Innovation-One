package com.github.carvaldo.fimo.datasource.remote.service

import com.github.carvaldo.fimo.datasource.remote.response.MovieDetail
import com.github.carvaldo.fimo.datasource.remote.response.SearchResult
import com.github.carvaldo.fimo.datasource.remote.util.ServiceGenerator
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
     *
     * @param query [String]
     * @return [Call]<[SearchResult]>
     */
    @GET("SearchMovie/"+ ServiceGenerator.API_KEY +"/{query}")
    fun search(@Path("query") query: String): Call<SearchResult>


    /**
     * Detail for movie by id.
     *
     * @param id [String]
     * @return [Call]<[MovieDetail]>
     */
    @GET("Title/"+ ServiceGenerator.API_KEY +"/{id}")
    fun detail(@Path("id") id: String): Call<MovieDetail>
}