package com.github.carvaldo.fimo.api.datasource.repository.local.dao

import com.github.carvaldo.fimo.api.datasource.repository.local.entity.ResultMovie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MovieResultDao: CrudRepository<ResultMovie, Long> {
    @Query("select rm from ResultMovie rm where rm.title like CONCAT('%',:title,'%')")
    fun findAllByByTitle(@Param("title") name: String): List<ResultMovie>
}