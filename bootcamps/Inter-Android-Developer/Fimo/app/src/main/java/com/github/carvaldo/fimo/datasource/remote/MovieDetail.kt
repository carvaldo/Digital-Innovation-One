package com.github.carvaldo.fimo.datasource.remote

import java.util.*

data class MovieDetail(
	val keywords: String? = null,
	val year: String? = null,
	val directors: String? = null,
	val genreList: List<GenreListItem?>? = null,
	val title: String? = null,
	val type: String? = null,
	val tvEpisodeInfo: Any? = null,
	val imDbRating: String? = null,
	val trailer: Any? = null,
	val runtimeStr: String? = null,
	val plotLocal: String? = null,
	val companies: String? = null,
	val plot: String? = null,
	val companyList: List<CompanyListItem?>? = null,
	val genres: String? = null,
	val ratings: Ratings? = null,
	val actorList: List<ActorListItem?>? = null,
	val imDbRatingVotes: String? = null,
	val tvSeriesInfo: Any? = null,
	val id: String? = null,
	val languageList: List<LanguageListItem?>? = null,
	val wikipedia: Wikipedia? = null,
	val fullCast: Any? = null,
	val image: String? = null,
	val fullTitle: String? = null,
	val images: Any? = null,
	val runtimeMins: String? = null,
	val languages: String? = null,
	val releaseDate: Date? = null,
	val similars: List<SimilarsItem?>? = null,
	val posters: Posters? = null,
	val errorMessage: String? = null,
	val metacriticRating: String? = null,
	val directorList: List<DirectorListItem?>? = null,
	val writers: String? = null,
	val stars: String? = null,
	val countries: String? = null,
	val countryList: List<CountryListItem?>? = null,
	val plotLocalIsRtl: Boolean? = null,
	val keywordList: List<String?>? = null,
	val originalTitle: String? = null,
	val awards: String? = null,
	val tagline: String? = null,
	val starList: List<StarListItem?>? = null,
	val contentRating: String? = null,
	val boxOffice: BoxOffice? = null,
	val writerList: List<WriterListItem?>? = null
)

data class PlotShort(
	val plainText: String? = null,
	val html: String? = null
)

data class PlotFull(
	val plainText: String? = null,
	val html: String? = null
)

data class BoxOffice(
	val grossUSA: String? = null,
	val openingWeekendUSA: String? = null,
	val cumulativeWorldwideGross: String? = null,
	val budget: String? = null
)

data class SimilarsItem(
	val imDbRating: String? = null,
	val image: String? = null,
	val fullTitle: String? = null,
	val year: String? = null,
	val plot: String? = null,
	val genres: String? = null,
	val directors: String? = null,
	val id: String? = null,
	val stars: String? = null,
	val title: String? = null
)

data class LanguageListItem(
	val value: String? = null,
	val key: String? = null
)

data class ActorListItem(
	val image: String? = null,
	val asCharacter: String? = null,
	val name: String? = null,
	val id: String? = null
)

data class Posters(
	val imDbId: String? = null,
	val fullTitle: String? = null,
	val year: String? = null,
	val backdrops: List<BackdropsItem?>? = null,
	val posters: List<PostersItem?>? = null,
	val errorMessage: String? = null,
	val title: String? = null,
	val type: String? = null
)

data class GenreListItem(
	val value: String? = null,
	val key: String? = null
)

data class BackdropsItem(
	val link: String? = null,
	val width: Int? = null,
	val aspectRatio: Double? = null,
	val language: String? = null,
	val id: String? = null,
	val height: Int? = null
)

data class WriterListItem(
	val name: String? = null,
	val id: String? = null
)

data class PostersItem(
	val link: String? = null,
	val width: Int? = null,
	val aspectRatio: Double? = null,
	val language: String? = null,
	val id: String? = null,
	val height: Int? = null
)

data class StarListItem(
	val name: String? = null,
	val id: String? = null
)

data class CountryListItem(
	val value: String? = null,
	val key: String? = null
)

data class Ratings(
	val imDbId: String? = null,
	val fullTitle: String? = null,
	val imDb: String? = null,
	val year: String? = null,
	val rottenTomatoes: String? = null,
	val metacritic: String? = null,
	val errorMessage: String? = null,
	val theMovieDb: String? = null,
	val filmAffinity: String? = null,
	val title: String? = null,
	val type: String? = null,
	val tVCom: String? = null
)

data class CompanyListItem(
	val name: String? = null,
	val id: String? = null
)

data class DirectorListItem(
	val name: String? = null,
	val id: String? = null
)

data class Wikipedia(
	val imDbId: String? = null,
	val titleInLanguage: Any? = null,
	val fullTitle: Any? = null,
	val plotFull: PlotFull? = null,
	val year: Any? = null,
	val errorMessage: String? = null,
	val language: Any? = null,
	val title: Any? = null,
	val type: Any? = null,
	val plotShort: PlotShort? = null,
	val url: Any? = null
)
