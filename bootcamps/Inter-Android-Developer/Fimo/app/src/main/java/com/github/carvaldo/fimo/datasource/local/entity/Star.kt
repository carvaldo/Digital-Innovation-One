package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "star", indices = [Index("apiId", unique = true)])
data class Star(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val apiId: String,
    val name: String)