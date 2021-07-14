package com.github.carvaldo.fimo.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val resultType: Int, // TODO: Mapear para Sealed Class
    val image: String, // TODO: Mapear para Uri
    val title: String,
    val description: String
)
