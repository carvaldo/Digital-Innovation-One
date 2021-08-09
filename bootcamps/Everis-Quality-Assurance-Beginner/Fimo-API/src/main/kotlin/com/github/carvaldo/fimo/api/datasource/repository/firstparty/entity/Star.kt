package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.Star as StarApi

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "star", indexes = [Index(columnList = "apiId", unique = true)])
data class Star(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var apiId: String,
    var name: String)

fun StarApi.transform() = Star(
    apiId = this.id,
    name = this.name
)