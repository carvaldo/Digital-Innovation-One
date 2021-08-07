package com.github.carvaldo.fimo.api.datasource.firstparty

import java.util.Date
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "cast_movie")
data class CastMovie(
    @Id var id: Long? = null,
    var role: String? = null,
    var year: Date? = null,
    var description: String? = null,
    var title: String? = null,
    var apiId: String? = null
)