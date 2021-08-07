package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.Director as DirectorApi

//TODO: Criar relacionamentos entre diretores, estrelas e filmes.

@Entity
@Table(name = "director", indexes= [Index(columnList = "apiId", unique = true)])
data class Director(
    @Id var id: Long? = null,
    var apiId: String,
    var name: String
)

fun DirectorApi.transform() = Director(
    apiId = this.id,
    name = this.name
)