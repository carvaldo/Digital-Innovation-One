package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "star_movie")
data class StarMovie(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var startId: String,
    var movieId: String)
