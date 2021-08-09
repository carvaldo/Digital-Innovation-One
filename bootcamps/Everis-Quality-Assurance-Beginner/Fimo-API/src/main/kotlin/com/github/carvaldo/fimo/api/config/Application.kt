package com.github.carvaldo.fimo.api.config

import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.MovieService
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.PersonService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// TODO: 09/08/2021 Implementar relacionamentos. 
// TODO: 09/08/2021 Melhorar scrapping para atualizar base de dados.
// TODO: 09/08/2021 Padronizar resposta para permitir descrição do erro. 

@Configuration
class Application {
    @Bean
    fun movieService(): MovieService = ServiceGenerator.create()

    @Bean
    fun personService(): PersonService = ServiceGenerator.create()
}