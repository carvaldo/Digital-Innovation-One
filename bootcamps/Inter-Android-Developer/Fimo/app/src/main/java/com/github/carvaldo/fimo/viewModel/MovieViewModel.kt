package com.github.carvaldo.fimo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.fimo.App
import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.local.entity.MovieDetail
import com.github.carvaldo.fimo.datasource.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val detail = MutableLiveData<Data<MovieDetail>>()

    fun findMovieDetail(apiId: String): LiveData<Data<MovieDetail>> = detail.also { data ->
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(movieRepository.findDetail(apiId))
        }
    }
}