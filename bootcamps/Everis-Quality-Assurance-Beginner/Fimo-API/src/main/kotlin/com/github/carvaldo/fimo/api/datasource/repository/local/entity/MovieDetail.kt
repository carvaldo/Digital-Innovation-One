package com.github.carvaldo.fimo.api.datasource.repository.local.entity

import org.hibernate.Hibernate
import java.util.Date
import javax.persistence.*
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity.MovieDetail as MovieDetailApi

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
	@Column(columnDefinition = "TEXT")
	var plotLocal: String? = null,
	@Column(columnDefinition = "TEXT")
	var companies: String? = null,
	var plot: String? = null,
	@Column(columnDefinition = "TEXT")
	var genres: String? = null,
	var image: String? = null,
	@Column(columnDefinition = "TEXT")
	var fullTitle: String? = null,
	var releaseDate: Date? = null,
	//val posters: Posters? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
		other as MovieDetail

		return id != null && id == other.id
	}

	override fun hashCode(): Int = 389651496

	@Override
	override fun toString(): String {
		return this::class.simpleName + "(id = $id , apiId = $apiId , title = $title , imDbRating = $imDbRating , trailer = $trailer , plotLocal = $plotLocal , companies = $companies , plot = $plot , genres = $genres , image = $image , fullTitle = $fullTitle , releaseDate = $releaseDate )"
	}
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
)
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