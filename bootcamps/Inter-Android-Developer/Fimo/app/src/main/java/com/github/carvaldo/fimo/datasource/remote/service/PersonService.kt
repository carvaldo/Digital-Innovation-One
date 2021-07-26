package com.github.carvaldo.fimo.datasource.remote.service

import com.github.carvaldo.fimo.datasource.remote.response.PersonData
import com.github.carvaldo.fimo.datasource.remote.util.ServiceGenerator
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Movie service
 *
 */
interface PersonService {

    /**
     * Get information of people (actor, actress, director, writers, ...).
     *
     * @param id Person api identification.
     * @return [Call]<[PersonData]>
     */
    @GET("Name/"+ ServiceGenerator.API_KEY +"/{id}")
    fun profile(@Path("id") id: String): Call<PersonData>
}