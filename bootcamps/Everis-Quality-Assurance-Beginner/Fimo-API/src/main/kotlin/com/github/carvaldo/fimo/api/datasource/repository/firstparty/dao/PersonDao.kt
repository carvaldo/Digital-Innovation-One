package com.github.carvaldo.fimo.api.datasource.repository.firstparty.dao

import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonDao: CrudRepository<Person, Long> {
    fun findPersonByApiId(apiId: String): Person?
}