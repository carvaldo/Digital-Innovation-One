package com.github.carvaldo.fimo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.carvaldo.fimo.App
import com.github.carvaldo.fimo.datasource.Data
import com.github.carvaldo.fimo.datasource.remote.response.PersonData
import com.github.carvaldo.fimo.datasource.remote.service.PersonService
import com.github.carvaldo.fimo.datasource.remote.util.ServiceGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel: ViewModel() {
    val person by lazy { MutableLiveData<Data<PersonData>>() }

    private val database by lazy { App.database }

    fun find(apiId: String) = person.apply {
        viewModelScope.launch(Dispatchers.IO) {
            val response = requestPerson(apiId)
            if (response.isSuccessful) {
                this@apply.postValue(Data(requestPerson(apiId).body(), null))
            } else {
                this@apply.postValue(Data(null, response.errorBody()?.string()))
            }
        }
    }

    private fun requestPerson(id: String) = ServiceGenerator.create<PersonService>().profile(id).execute()
}