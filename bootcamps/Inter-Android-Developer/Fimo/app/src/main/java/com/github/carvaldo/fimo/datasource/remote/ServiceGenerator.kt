package com.github.carvaldo.fimo.datasource.remote

import com.github.carvaldo.fimo.BuildConfig
import com.google.gson.ExclusionStrategy
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.jackson.JacksonConverterFactory

class ServiceGenerator {
    companion object {
        const val API_KEY = "k_bljoq3nq"

        private var client = OkHttpClient.Builder()
            .let {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(OkHttpProfilerInterceptor())
                }
                it.build()
            }
        var retrofit: Retrofit = Retrofit.Builder().let {
            it.baseUrl("https://imdb-api.com/pt-BR/API/")
            it.client(client)
            //it.addConverterFactory(JacksonConverterFactory.create())
            it.addConverterFactory(
                GsonConverterFactory.create(GsonBuilder()
                .enableComplexMapKeySerialization().create()))
            it.build()
        }

        inline fun <reified T> create(): T = retrofit.create(T::class.java)
    }
}