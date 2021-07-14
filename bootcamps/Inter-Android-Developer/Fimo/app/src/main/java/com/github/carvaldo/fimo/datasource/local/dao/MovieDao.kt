package com.github.carvaldo.fimo.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.carvaldo.fimo.datasource.local.Result

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(result: Result): Long

    @Delete
    fun remover(result: Result)

    @Query("SELECT * FROM result")
    fun listar(): LiveData<List<Result>>
}