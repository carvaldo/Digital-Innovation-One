package com.github.carvaldo.fimo.api.config

import com.github.carvaldo.fimo.api.datasource.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service.MovieService
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.service.PersonService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun movieService(): MovieService = ServiceGenerator.create()

    @Bean
    fun personService(): PersonService = ServiceGenerator.create()
}