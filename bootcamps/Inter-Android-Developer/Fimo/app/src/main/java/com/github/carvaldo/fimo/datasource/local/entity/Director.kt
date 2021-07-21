package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.carvaldo.fimo.datasource.remote.response.Director as DirectorApi

//TODO: Criar relacionamentos entre diretores, estrelas e filmes.

@Entity(tableName = "director", indices = [Index("apiId", unique = true)])
data class Director(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val apiId: String,
    val name: String
)

fun DirectorApi.transform() = Director(
    apiId = this.id,
    name = this.name
)