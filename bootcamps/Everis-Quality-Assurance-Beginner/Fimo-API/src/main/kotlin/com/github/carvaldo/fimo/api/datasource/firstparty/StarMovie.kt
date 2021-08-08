package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "star_movie")
data class StarMovie(
    @Id var id: Long? = null,
    var startId: String,
    var movieId: String)
