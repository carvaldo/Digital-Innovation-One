package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.remote.response.Movie

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

fun Movie.transform() = ResultMovie(
    remoteId = this.id,
    resultType = ResultType.TITLE,
    image = this.image,
    title = this.title,
    description = this.description
)