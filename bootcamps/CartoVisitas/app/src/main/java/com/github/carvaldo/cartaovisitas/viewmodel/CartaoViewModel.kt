package com.github.carvaldo.cartaovisitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.cartaovisitas.App
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.data.repository.CartaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class CartaoViewModel: ViewModel() {
    private val cartaoRepository by lazy { CartaoRepository(App.database) }

    fun salvar(data: Cartao.() -> Unit) = Cartao().apply {
        data()
        viewModelScope.launch(Dispatchers.IO) { cartaoRepository.salvar(this@apply) }
    }

    fun listar() = cartaoRepository.listar()
}