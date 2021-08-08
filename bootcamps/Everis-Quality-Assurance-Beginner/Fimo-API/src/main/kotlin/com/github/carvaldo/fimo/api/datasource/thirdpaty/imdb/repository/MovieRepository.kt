package com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository

import com.github.carvaldo.fimo.api.datasource.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service.MovieService
import org.springframework.stereotype.Service

@Service
class MovieRepository constructor(private val service: MovieService) {

    fun search(query: String) = service.search(ServiceGenerator.API_KEY, query).execute()

    fun findDetail(apiId: String) = requestRemoteData(apiId).execute()

    private fun requestRemoteData(apiId: String) = service.detail(ServiceGenerator.API_KEY, apiId)
}