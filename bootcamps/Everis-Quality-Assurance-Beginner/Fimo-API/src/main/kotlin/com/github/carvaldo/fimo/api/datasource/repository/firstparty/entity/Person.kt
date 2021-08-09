package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "person", indexes = [Index(columnList = "apiId", unique = true)])
data class Person (
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
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