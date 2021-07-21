package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Searched Search historic
 *
 * @property id
 * @property query
 * @property type Api Type
 */
@Entity(tableName = "searched")
data class Searched(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val query: String,
    val type: String)
