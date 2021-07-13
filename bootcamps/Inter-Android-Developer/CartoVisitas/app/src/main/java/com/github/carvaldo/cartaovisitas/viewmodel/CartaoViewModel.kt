package com.github.carvaldo.cartaovisitas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.cartaovisitas.App
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.data.repository.CartaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartaoViewModel: ViewModel() {
    private val cartaoRepository by lazy { CartaoRepository(App.database) }
    val cartao by lazy { MutableLiveData<Cartao>() }

    fun salvar(data: Cartao.() -> Unit) = Cartao().apply {
        data()
        cartao.value = this@apply
        viewModelScope.launch(Dispatchers.IO) {
            cartao.value?.id = cartaoRepository.salvar(this@apply)
            cartao.postValue(cartao.value)
        }
    }

    fun excluir(cartao: Cartao) {
        viewModelScope.launch(Dispatchers.IO) { cartaoRepository.excluir(cartao) }
    }

    fun listar() = cartaoRepository.listar()
}