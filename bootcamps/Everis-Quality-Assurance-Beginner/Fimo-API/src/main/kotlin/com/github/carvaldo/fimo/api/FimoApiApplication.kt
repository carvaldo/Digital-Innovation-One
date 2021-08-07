package com.github.carvaldo.fimo.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FimoApiApplication

fun main(args: Array<String>) {
    runApplication<FimoApiApplication>(*args)
}
