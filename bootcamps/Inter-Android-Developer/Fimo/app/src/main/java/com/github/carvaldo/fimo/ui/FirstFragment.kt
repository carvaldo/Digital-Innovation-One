package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.fimo.databinding.FragmentFirstBinding
import com.github.carvaldo.fimo.datasource.remote.Movie
import com.github.carvaldo.fimo.datasource.remote.MovieService
import com.github.carvaldo.fimo.datasource.remote.SearchResult
import com.github.carvaldo.fimo.datasource.remote.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val adapter by lazy { SearchAdapter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter.onItemClickListener = { _: Int, movie: Movie ->
            Log.d("WTF", "onCreateView: click")
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(movie.id))
        }
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ServiceGenerator.create<MovieService>().search("Titanic").enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                adapter.items = response.body()?.results
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}