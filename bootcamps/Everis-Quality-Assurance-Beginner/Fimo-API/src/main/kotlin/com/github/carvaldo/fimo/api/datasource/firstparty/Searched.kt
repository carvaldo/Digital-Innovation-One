package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Searched Search historic
 */
@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "searched")
data class Searched(
    @Id var id: Long? = null,
    var query: String,
    var type: String)
