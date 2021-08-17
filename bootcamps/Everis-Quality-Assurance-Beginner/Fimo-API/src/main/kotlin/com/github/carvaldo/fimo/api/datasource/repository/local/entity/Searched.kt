package com.github.carvaldo.fimo.api.datasource.repository.local.entity

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

/**
 * Searched Search historic
 */
@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "searched")
data class Searched(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    var query: String,
    var type: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Searched

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 1299841978

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , query = $query , type = $type )"
    }
}
