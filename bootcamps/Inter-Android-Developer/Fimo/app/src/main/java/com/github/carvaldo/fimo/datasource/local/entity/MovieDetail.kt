package com.github.carvaldo.fimo.datasource.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*
import com.github.carvaldo.fimo.datasource.remote.response.MovieDetail as MovieDetailApi

@Entity(tableName = "movie_detail", indices = [Index("apiId", unique = true)])
data class MovieDetail(
	@PrimaryKey(autoGenerate = false)
	val id: Long,
	val apiId: String,
	val title: String? = null,
	//val type: String? = null,
	val imDbRating: String? = null,
	val trailer: String? = null,
	val plotLocal: String? = null,
	val companies: String? = null,
	val plot: String? = null,
	val genres: String? = null,
	val image: String? = null,
	val fullTitle: String? = null,
	val releaseDate: Date? = null,
	//val posters: Posters? = null
) {
	@Ignore
	var directors: List<Director>? = null
	@Ignore
	var stars: List<Star>? = null
}

fun MovieDetailApi.transform() = MovieDetail(
	id = this.id,
	apiId = this.apiId,
	image = this.image,
	title = this.title,
	companies = this.companies,
	fullTitle = this.fullTitle,
	genres = this.genres,
	imDbRating = this.imDbRating,
	plot = this.plot,
	plotLocal = this.plotLocal,
	releaseDate = this.releaseDate,
	trailer = this.trailer
)//.also {
//	it.directors = this.directorList?.map { item -> item.transform() }
//	it.stars = this.starList?.map { item -> item.transform() }
//}

//data class Posters(
//	val imDbId: String? = null,
//	val fullTitle: String? = null,
//	val year: String? = null,
//	val backdrops: List<BackdropsItem?>? = null,
//	val posters: List<PostersItem?>? = null,
//	val errorMessage: String? = null,
//	val title: String? = null,
//	val type: String? = null
//)