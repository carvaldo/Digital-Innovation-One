package com.github.carvaldo.fimo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.remote.response.PersonData
import com.github.carvaldo.fimo.datasource.remote.service.PersonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val service: PersonService
): ViewModel() {
    val person by lazy { MutableLiveData<Data<PersonData>>() }

    fun find(apiId: String) = person.apply {
        viewModelScope.launch(Dispatchers.IO) {
            val response = requestPerson(apiId)
            if (response.isSuccessful) {
                this@apply.postValue(Data(requestPerson(apiId).body()!!.data, null))
            } else {
                this@apply.postValue(Data(null, response.errorBody()?.string()))
            }
        }
    }

    // TODO: Alocar em um repository
    private fun requestPerson(id: String) = service.profile(id).execute()
}