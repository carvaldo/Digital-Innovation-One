package com.github.carvaldo.fimo.api.datasource.repository.local.entity

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Index
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Searched result in historic.
 */
@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "searched_result", indexes = [Index(columnList = "resultId", unique = false)])
data class SearchedResult(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var searchId: Long,
    var resultId: Long) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as SearchedResult

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 176632567

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , searchId = $searchId , resultId = $resultId )"
    }
}
