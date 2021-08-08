package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.Star as StarApi

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "star", indexes = [Index(columnList = "apiId", unique = true)])
data class Star(
    @Id var id: Long? = null,
    var apiId: String,
    var name: String)

fun StarApi.transform() = Star(
    apiId = this.id,
    name = this.name
)