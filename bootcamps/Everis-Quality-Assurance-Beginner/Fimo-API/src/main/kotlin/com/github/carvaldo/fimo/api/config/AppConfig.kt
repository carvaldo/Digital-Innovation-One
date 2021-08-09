package com.github.carvaldo.fimo.api.config

import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.MovieService
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.PersonService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// TODO: 09/08/2021 Implementar relacionamentos. 

@Configuration
class AppConfig {
    @Bean
    fun movieService(): MovieService = ServiceGenerator.create()

    @Bean
    fun personService(): PersonService = ServiceGenerator.create()
}