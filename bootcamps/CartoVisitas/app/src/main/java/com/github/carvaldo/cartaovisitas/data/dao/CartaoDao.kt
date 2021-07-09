package com.github.carvaldo.cartaovisitas.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.carvaldo.cartaovisitas.data.Cartao

@Dao
interface CartaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(cartao: Cartao): Long

    @Query("SELECT * FROM cartao")
    fun listar(): LiveData<List<Cartao>>
}