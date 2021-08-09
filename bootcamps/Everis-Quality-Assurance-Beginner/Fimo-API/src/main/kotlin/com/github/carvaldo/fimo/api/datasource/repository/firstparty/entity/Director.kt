package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.Director as DirectorApi

//TODO: Criar relacionamentos entre diretores, estrelas e filmes.

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "director", indexes= [Index(columnList = "apiId", unique = true)])
data class Director(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var apiId: String,
    var name: String
)

fun DirectorApi.transform() = Director(
    apiId = this.id,
    name = this.name
)