package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.fimo.databinding.FragmentFirstBinding
import com.github.carvaldo.fimo.datasource.local.ResultMovie
import com.github.carvaldo.fimo.viewModel.SearchViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val adapter by lazy { SearchAdapter() }
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter.onItemClickListener = { _: Int, movie: ResultMovie ->
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(movie.remoteId!!))
        }
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Implementar barra de buscas.
        viewModel.searchAsync("panico").observe(requireActivity()) {
            if (activity != null) {
                when {
                    it.errorMessage != null -> {
                        TODO("Mostrar mensagem de falha na solicitação.")
                    }
                    else -> {
                        adapter.items = it.data
                    }
                }
            }
        }
    }
}