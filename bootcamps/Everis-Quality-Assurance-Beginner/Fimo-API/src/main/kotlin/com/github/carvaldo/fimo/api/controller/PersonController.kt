package com.github.carvaldo.fimo.api.controller

import com.github.carvaldo.fimo.api.datasource.Data
import com.github.carvaldo.fimo.api.datasource.repository.MovieRepository
import com.github.carvaldo.fimo.api.datasource.repository.PersonRepository
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.MovieDetail
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.Person
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.ResultMovie
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("persons")
class PersonController constructor(private val personRepository: PersonRepository) {

    @GetMapping("/profile/{id}")
    fun getDetail(@PathVariable("id") id: String): ResponseEntity<Data<Person>> {
        return try {
            ResponseEntity.ok(Data(personRepository.getProfile(id)))
        } catch (e: LimitReachedException) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Data(null, e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.badRequest().body(Data(null, "Erro interno."))
        }
    }
}