package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "star_movie")
data class StarMovie(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var startId: String,
    var movieId: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as StarMovie

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1439025435

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , startId = $startId , movieId = $movieId )"
    }
}
