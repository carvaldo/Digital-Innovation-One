package com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service

import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.PersonData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonService {

    /**
     * Get information of people (actor, actress, director, writers, ...).
     */
    @GET("Name/{key}/{id}")
    fun profile(@Path("key") key: String, @Path("id") id: String): Call<PersonData>
}