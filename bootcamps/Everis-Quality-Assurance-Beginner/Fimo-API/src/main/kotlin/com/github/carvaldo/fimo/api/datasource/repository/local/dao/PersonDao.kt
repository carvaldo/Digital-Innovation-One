package com.github.carvaldo.fimo.api.datasource.repository.local.dao

import com.github.carvaldo.fimo.api.datasource.repository.local.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonDao: CrudRepository<Person, Long> {
    fun findPersonByApiId(apiId: String): Person?
}