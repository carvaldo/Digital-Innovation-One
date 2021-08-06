package com.github.carvaldo.fimo.ui

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.fimo.R
import com.github.carvaldo.fimo.databinding.FragmentFirstBinding
import com.github.carvaldo.fimo.datasource.local.entity.ResultMovie
import com.github.carvaldo.fimo.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private val TAG = FirstFragment::class.simpleName

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentFirstBinding
    @Inject lateinit var adapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter.onItemClickListener = { _: Int, movie: ResultMovie ->
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(movie.remoteId!!))
        }
        binding.recyclerView.adapter = adapter
        setHasOptionsMenu(true)
        // TODO: Carregar última pesquisa.
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val searchManager = getSystemService(requireContext(), SearchManager::class.java)
        (menu.findItem(R.id.search).actionView as SearchView).also {
            it.setSearchableInfo(searchManager?.getSearchableInfo(requireActivity().componentName))
            it.setOnQueryTextListener(this)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // TODO: Mostrar loading.
        query?.also {
            viewModel.searchAsync(it).observe(this) { response ->
                if (activity != null && !requireActivity().isFinishing) {
                    when {
                        response.errorMessage != null -> TODO("Mostrar mensagem de falha na solicitação.")
                        else -> adapter.items = response.data
                    }
                }
            }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}