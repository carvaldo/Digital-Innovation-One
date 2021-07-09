package com.github.carvaldo.cartaovisitas.viewmodel

import androidx.lifecycle.ViewModel
import com.github.carvaldo.cartaovisitas.App
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.data.DatabaseApp
import com.github.carvaldo.cartaovisitas.data.`package`.CartaoRepository

class CartaoViewModel: ViewModel() {
    private val cartaoRepository by lazy { CartaoRepository(App.database) }

    fun salvar(data: Cartao.() -> Unit) = Cartao().apply {
        data()
        cartaoRepository.salvar(this)
    }

    fun listar() = cartaoRepository.listar()
}