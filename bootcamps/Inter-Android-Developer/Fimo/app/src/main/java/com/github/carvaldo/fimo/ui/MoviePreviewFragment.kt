package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.carvaldo.fimo.databinding.ChipItemPersonBinding
import com.github.carvaldo.fimo.databinding.FragmentSecondBinding
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.viewModel.MovieViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoviePreviewFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val dateFormat by lazy { SimpleDateFormat("MM/yyyy", Locale("pt", "BR")) }
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }

    private fun loadData() {
        viewModel.findMovieDetail(MoviePreviewFragmentArgs.fromBundle(requireArguments()).id).observe(requireActivity()) {
            if (!it.errorMessage.isNullOrBlank()) {
                TODO("Feedback")
            } else if (it.data != null){
                bindView(it.data)
            }
        }
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
        movieDetail.stars?.forEach {
            val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
            itemBinding.root.text = it.name
            binding.actorChipGroup.addView(itemBinding.root)
        }
        movieDetail.directors?.forEach {
            val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
            itemBinding.root.text = it.name
            binding.directorChipGroup.addView(itemBinding.root)
        }
    }
}