package com.github.carvaldo.fimo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.carvaldo.fimo.databinding.ChipItemPersonBinding
import com.github.carvaldo.fimo.databinding.FragmentPreviewMovieBinding
import com.github.carvaldo.fimo.datasource.local.entity.Director
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.datasource.local.entity.Star
import com.github.carvaldo.fimo.viewModel.MovieViewModel
import java.text.SimpleDateFormat
import java.util.*

private val TAG = MoviePreviewFragment::class.simpleName

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoviePreviewFragment : Fragment() {
    private lateinit var binding: FragmentPreviewMovieBinding
    private val dateFormat by lazy { SimpleDateFormat("MM/yyyy", Locale("pt", "BR")) }
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentPreviewMovieBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }

    private fun loadData() {
        viewModel.findMovieDetail(MoviePreviewFragmentArgs.fromBundle(requireArguments()).id)
            .observe(requireActivity()) {
                if (!it.errorMessage.isNullOrBlank()) {
                    TODO("Feedback")
                } else if (it.data != null){
                    bindView(it.data)
                }
            }
    }

    private fun bindView(movieDetail: MovieDetail) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = movieDetail.title
        binding.mainImage.uri = movieDetail.image?.toUri()
        binding.descriptionText.text = movieDetail.plotLocal
        binding.companyText.text = movieDetail.companies
        binding.genreText.text = movieDetail.genres
        binding.ratingText.text = movieDetail.imDbRating
        movieDetail.releaseDate?.also {
            binding.releaseDateText.text = dateFormat.format(it)
        }
        movieDetail.stars?.forEach { bindChipView(it) }
        movieDetail.directors?.forEach { bindChipView(it) }
    }

    private fun bindChipView(star: Star) {
        val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
        itemBinding.root.text = star.name
        itemBinding.root.setOnClickListener { navigateToProfile(star.apiId) }
        binding.actorChipGroup.addView(itemBinding.root)
    }

    private fun bindChipView(director: Director) {
        val itemBinding = ChipItemPersonBinding.inflate(layoutInflater, binding.root, false)
        itemBinding.root.text = director.name
        itemBinding.root.setOnClickListener { navigateToProfile(director.apiId) }
        binding.directorChipGroup.addView(itemBinding.root)
    }

    private fun navigateToProfile(id: String) {
        findNavController().navigate(MoviePreviewFragmentDirections.actionSecondFragmentToPersonPreviewFragment(id))
    }
}