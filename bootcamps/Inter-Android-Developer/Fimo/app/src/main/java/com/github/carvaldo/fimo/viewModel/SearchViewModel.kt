package com.github.carvaldo.fimo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.fimo.App
import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.local.entity.ResultMovie
import com.github.carvaldo.fimo.datasource.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: MovieRepository
): ViewModel() {

    fun searchAsync(query: String) = MutableLiveData<Data<List<ResultMovie>>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            this@apply.postValue(searchRepository.searchAsync(query))
        }
    }
}