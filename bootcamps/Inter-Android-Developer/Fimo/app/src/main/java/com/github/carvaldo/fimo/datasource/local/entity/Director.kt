package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//TODO: Criar relacionamentos entre diretores, estrelas e filmes.

@Entity(tableName = "director", indices = [Index("apiId", unique = true)])
data class Director(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val apiId: String,
    val name: String
)