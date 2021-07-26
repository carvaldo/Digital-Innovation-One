package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "person",
    indices = [Index("apiId", unique = true)])
data class Person (

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String,
    var role: String? = null,
    var birthDate: Date? = null,
    var deathDate: Date? = null,
    var description: String? = null,
    var apiId: String? = null
) {
    @Ignore
    var cast: List<CastMovie>? = null
}