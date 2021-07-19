package com.github.carvaldo.fimo.datasource.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Movie detail service
 *
 */
interface MovieDetailService {
    /**
     * Search movie
     *
     * @param id
     * @return [Call]<[MovieDetail]>
     */
    @GET("Title/"+ ServiceGenerator.API_KEY +"/{id}/Posters,Ratings,Wikipedia")
    fun search(@Path("id") id: String): Call<MovieDetail>
}