package com.github.carvaldo.fimo.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.carvaldo.fimo.datasource.ResultType

/**
 * Result movie POJO
 *
 * @property id Primery key
 * @property remoteId API id
 * @property resultType API result type
 * @property image Banner
 * @property title
 * @property description
 */
@Entity(tableName = "result")
data class ResultMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val remoteId: String? = null,
    val resultType: ResultType? = null,
    val image: String? = null, // TODO: Mapear para Uri?
    val title: String? = null,
    val description: String? = null
)
