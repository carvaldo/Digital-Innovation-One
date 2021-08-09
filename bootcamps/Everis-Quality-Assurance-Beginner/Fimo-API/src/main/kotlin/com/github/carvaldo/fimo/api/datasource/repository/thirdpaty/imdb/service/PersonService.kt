package com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service

import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.PersonData
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