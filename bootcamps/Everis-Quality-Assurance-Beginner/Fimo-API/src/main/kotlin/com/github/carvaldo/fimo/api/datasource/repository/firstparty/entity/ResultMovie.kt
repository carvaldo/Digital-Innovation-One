package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import com.github.carvaldo.fimo.api.datasource.ResultType
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.Movie
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "result")
data class ResultMovie(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var remoteId: String? = null,
    var resultType: ResultType? = null,
    var image: String? = null, // TODO: Mapear para Uri?
    var title: String? = null,
    var description: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ResultMovie

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 867841428

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , remoteId = $remoteId , resultType = $resultType , image = $image , title = $title , description = $description )"
    }
}

fun Movie.transform() = ResultMovie(
    remoteId = this.id,
    resultType = ResultType.TITLE,
    image = this.image,
    title = this.title,
    description = this.description
)