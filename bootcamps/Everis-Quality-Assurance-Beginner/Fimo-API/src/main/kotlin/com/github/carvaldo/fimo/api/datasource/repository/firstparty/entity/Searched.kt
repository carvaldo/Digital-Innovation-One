package com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity

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
    var type: String)
