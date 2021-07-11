package com.github.carvaldo.cartaovisitas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.cartaovisitas.R
import com.github.carvaldo.cartaovisitas.data.Cartao
import com.github.carvaldo.cartaovisitas.databinding.FragmentListBinding
import com.github.carvaldo.cartaovisitas.viewmodel.CartaoViewModel

/**
 * [Fragment] da lista de cartões.
 */
class ListFragment : BaseFragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: CartaoAdapter
    private val viewModel: CartaoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun registrarListeners() {
        binding.adicionarButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListaParaAdicao())
        }
    }

    override fun observar() {
        viewModel.listar().observe(requireActivity(), ::configurarLista)
    }

    override fun processarArgumentos() {
    }

    private fun configurarLista(itens: List<Cartao>) {
        adapter = CartaoAdapter(lifecycleScope, itens)
        adapter.onItemClickListener = { _: Int, cartao: Cartao ->
            findNavController().navigate(ListFragmentDirections.firstToEdit(cartao))
        }
        binding.recyclerView.adapter = adapter
    }

}