package com.github.carvaldo.fimo.api.datasource.firstparty

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

/**
 * Searched result in historic.
 */
@Suppress("JpaDataSourceORMInspection")
@Entity
@Table(name = "searched_result", indexes = [Index(columnList = "resultId", unique = false)])
data class SearchedResult(
    @Id var id: Long? = null,
    var searchId: Long,
    var resultId: Long)
