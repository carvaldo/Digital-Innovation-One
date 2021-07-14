package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.carvaldo.fimo.databinding.FragmentFirstBinding
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

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ServiceGenerator.create<MovieService>().search("Titanic").enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                binding.recyclerView.adapter = SearchAdapter(response.body()?.results)
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}