package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "director_movie")
data class DirectorMovie(
    @Id var id: Long? = null,
    var directorId: String,
    var movieId: String
 )
