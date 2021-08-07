package com.github.carvaldo.fimo.api.datasource.firstparty

import com.github.carvaldo.fimo.api.datasource.ResultType
import com.github.carvaldo.fimo.api.datasource.thirdpaty.imdb.Movie
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "result")
data class ResultMovie(
    @Id var id: Long? = null,
    var remoteId: String? = null,
    var resultType: ResultType? = null,
    var image: String? = null, // TODO: Mapear para Uri?
    var title: String? = null,
    var description: String? = null
)

fun Movie.transform() = ResultMovie(
    remoteId = this.id,
    resultType = ResultType.TITLE,
    image = this.image,
    title = this.title,
    description = this.description
)