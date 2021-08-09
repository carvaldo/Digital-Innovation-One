package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

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
    var resultId: Long)
