package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.carvaldo.fimo.datasource.ResultType
import com.github.carvaldo.fimo.datasource.remote.response.SearchResultMovie as SearchMovieResultApi

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
@Entity(tableName = "search_result_movie")
data class SearchResultMovie(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val apiId: String? = null,
    val resultType: ResultType? = null,
    val image: String? = null, // TODO: Mapear para Uri
    val title: String? = null,
    val description: String? = null
)

fun SearchMovieResultApi.convertToLocalData() = SearchResultMovie(
    apiId = this.remoteId,
    resultType = ResultType.TITLE,
    image = this.image,
    title = this.title,
    description = this.description
)