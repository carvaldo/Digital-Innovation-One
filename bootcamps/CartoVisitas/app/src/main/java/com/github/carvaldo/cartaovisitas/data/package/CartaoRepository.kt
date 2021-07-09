package com.github.carvaldo.cartaovisitas.data.`package`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.data.DatabaseApp
import com.github.carvaldo.cartaovisitas.data.dao.CartaoDao
import kotlin.concurrent.thread

class CartaoRepository(private val databaseApp: DatabaseApp) {
    private val cartaoDao by lazy { databaseApp.getCartaoDao() }

    fun salvar(cartao: Cartao) {
        thread(true) {
            cartaoDao.salvar(cartao)
        }
    }

    fun listar() = cartaoDao.listar()
}