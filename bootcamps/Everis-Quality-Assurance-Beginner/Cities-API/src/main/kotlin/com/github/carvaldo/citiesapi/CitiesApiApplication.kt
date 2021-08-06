package com.github.carvaldo.citiesapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CitiesApiApplication

fun main(args: Array<String>) {
    runApplication<CitiesApiApplication>(*args)
}
