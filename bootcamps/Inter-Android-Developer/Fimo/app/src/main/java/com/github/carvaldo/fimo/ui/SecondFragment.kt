package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.carvaldo.fimo.databinding.FragmentSecondBinding
import com.github.carvaldo.fimo.datasource.remote.MovieDetail
import com.github.carvaldo.fimo.datasource.remote.MovieDetailService
import com.github.carvaldo.fimo.datasource.remote.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        load()
        return binding.root
    }

    private fun load() {
        ServiceGenerator.create<MovieDetailService>().search(SecondFragmentArgs.fromBundle(requireArguments()).id).enqueue(object :
            Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                bindView(response.body()!!)
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun bindView(movieDetail: MovieDetail) {
        binding.titleText.text = movieDetail.title
        binding.descriptionText.text = movieDetail.plotLocal
        binding.companyText.text = movieDetail.companies
        binding.genreText.text = movieDetail.genres
        binding.ratingText.text = movieDetail.imDbRating
        binding.releaseDateText.text = movieDetail.releaseDate
    }
}