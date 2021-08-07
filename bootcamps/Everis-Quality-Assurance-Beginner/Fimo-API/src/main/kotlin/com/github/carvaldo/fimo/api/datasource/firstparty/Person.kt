package com.github.carvaldo.fimo.api.datasource.firstparty

import com.github.carvaldo.fimo.api.datasource.firstparty.CastMovie
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = "person", indexes = [Index(columnList = "apiId", unique = true)])
data class Person (
    @Id var id: Long? = null,
    var name: String,
    var role: String? = null,
    var birthDate: Date? = null,
    var deathDate: Date? = null,
    var description: String? = null,
    var apiId: String? = null
) {
    @Transient
    var cast: List<CastMovie>? = null
}