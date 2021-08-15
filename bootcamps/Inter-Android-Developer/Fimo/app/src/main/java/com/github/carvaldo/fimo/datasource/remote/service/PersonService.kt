package com.github.carvaldo.fimo.datasource.remote.service

import com.github.carvaldo.fimo.datasource.remote.response.PersonData
import com.github.carvaldo.fimo.datasource.remote.ServiceGenerator
import com.github.carvaldo.fimo.datasource.remote.response.ResponseApi
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
    @GET("persons/profile/{id}")
    fun profile(@Path("id") id: String): Call<ResponseApi<PersonData>>
}