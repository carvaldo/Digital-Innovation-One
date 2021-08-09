package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import java.util.Date
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.MovieDetail as MovieDetailApi

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "movie_detail", indexes = [Index(columnList = "apiId", unique = true)])
data class MovieDetail(
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
	var apiId: String,
	var title: String? = null,
	//val type: String? = null,
	var imDbRating: String? = null,
	var trailer: String? = null,
	var plotLocal: String? = null,
	var companies: String? = null,
	var plot: String? = null,
	var genres: String? = null,
	var image: String? = null,
	var fullTitle: String? = null,
	var releaseDate: Date? = null,
	//val posters: Posters? = null
) {
	@Transient
	var directors: List<Director>? = null
	@Transient
	var stars: List<Star>? = null
}

fun MovieDetailApi.transform() = MovieDetail(
	apiId = this.id!!,
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
).also {
	it.directors = this.directorList?.map { item -> item.transform() }
	it.stars = this.starList?.map { item -> item.transform() }
}

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