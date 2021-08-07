package com.github.carvaldo.fimo.api.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("paises")
class Country {

    @GetMapping("/")
    fun index(): ResponseEntity<Unit> = ResponseEntity.ok().build()
}