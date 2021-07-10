package com.github.carvaldo.cartaovisitas.data.repository

import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.data.DatabaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class CartaoRepository(private val databaseApp: DatabaseApp) {
    private val cartaoDao by lazy { databaseApp.getCartaoDao() }

    fun salvar(cartao: Cartao) = cartaoDao.salvar(cartao)

    fun listar() = cartaoDao.listar()
}