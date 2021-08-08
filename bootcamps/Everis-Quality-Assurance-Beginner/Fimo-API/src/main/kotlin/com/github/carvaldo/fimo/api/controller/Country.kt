package com.github.carvaldo.fimo.api.controller

import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.PersonData
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.repository.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("paises")
class Country constructor(private val personRepository: PersonRepository) {

    @GetMapping("/")
    fun index(): ResponseEntity<PersonData> = personRepository.find("nm0000154").let { response ->
        return@let when {
            response.isSuccessful -> {
                val body = response.body()
                if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    println("wtf: CASO 1")
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
                } else {
                    println("wtf: CASO 2")
                    ResponseEntity.ok(body)
                }
            }
            else -> {
                println("wtf: CASO 3")
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
            }
        }
    }
}