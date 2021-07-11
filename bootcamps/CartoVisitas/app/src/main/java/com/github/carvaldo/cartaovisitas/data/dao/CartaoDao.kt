package com.github.carvaldo.cartaovisitas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.carvaldo.cartaovisitas.data.Cartao

@Dao
interface CartaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(cartao: Cartao): Long

    @Delete
    fun remover(cartao: Cartao)

    @Query("SELECT * FROM cartao")
    fun listar(): LiveData<List<Cartao>>
}