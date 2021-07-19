package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.carvaldo.fimo.databinding.ChipItemPersonBinding
import com.github.carvaldo.fimo.databinding.FragmentSecondBinding
import com.github.carvaldo.fimo.datasource.remote.MovieDetail
import com.github.carvaldo.fimo.datasource.remote.MovieDetailService
import com.github.carvaldo.fimo.datasource.remote.ServiceGenerator
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoviePreviewFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val dateFormat by lazy { SimpleDateFormat("MM/yyyy", Locale("pt", "BR")) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }

    private fun loadData() {
        ServiceGenerator.create<MovieDetailService>().search(MoviePreviewFragmentArgs.fromBundle(requireArguments()).id).enqueue(object :
            Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                val body = response.body()!!
                if (!body.errorMessage.isNullOrBlank()) {
                    TODO("Feedback")
                } else {
                    bindView(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun bindView(movieDetail: MovieDetail) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = movieDetail.title
        Picasso.get().load(movieDetail.image).into(binding.mainImage)
        binding.descriptionText.text = movieDetail.plotLocal
        binding.companyText.text = movieDetail.companies
        binding.genreText.text = movieDetail.genres
        binding.ratingText.text = movieDetail.imDbRating
        movieDetail.releaseDate?.also {
            binding.releaseDateText.text = dateFormat.format(it)
        }
        movieDetail.starList?.forEach {
            val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
            itemBinding.root.text = it!!.name
            binding.actorChipGroup.addView(itemBinding.root)
        }
        movieDetail.directorList?.forEach {
            val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
            itemBinding.root.text = it!!.name
            binding.directorChipGroup.addView(itemBinding.root)
        }
    }
}