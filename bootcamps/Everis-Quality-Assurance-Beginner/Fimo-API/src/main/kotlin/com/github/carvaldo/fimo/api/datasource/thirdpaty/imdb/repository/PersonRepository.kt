package com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository

import com.github.carvaldo.fimo.api.datasource.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service.PersonService
import org.springframework.stereotype.Service

@Service
class PersonRepository constructor(private val starService: PersonService) {

    fun find(apiId: String) = starService.profile(ServiceGenerator.API_KEY, apiId).execute()
}