package com.github.carvaldo.fimo.api.datasource.repository.local.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "director_movie")
data class DirectorMovie(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var directorId: String,
    var movieId: String
 )
