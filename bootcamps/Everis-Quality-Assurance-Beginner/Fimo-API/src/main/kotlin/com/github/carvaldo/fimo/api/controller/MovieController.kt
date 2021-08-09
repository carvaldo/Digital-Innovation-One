package com.github.carvaldo.fimo.api.controller

import com.github.carvaldo.fimo.api.datasource.repository.MovieRepository
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.ResultMovie
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("movies")
class MovieController constructor(private val movieRepository: MovieRepository) {

    @GetMapping("/search/{query}")
    fun search(@PathVariable("query") query: String): ResponseEntity<List<ResultMovie>> {
        return try {
            ResponseEntity.ok(movieRepository.searchByName(query))
        } catch (e: LimitReachedException) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.badRequest().build()
        }
    }
}