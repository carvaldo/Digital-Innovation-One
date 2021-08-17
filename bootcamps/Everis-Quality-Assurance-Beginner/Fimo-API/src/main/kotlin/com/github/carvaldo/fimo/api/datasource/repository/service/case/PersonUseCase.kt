package com.github.carvaldo.fimo.api.datasource.repository.service.case

import com.github.carvaldo.fimo.api.datasource.repository.local.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.service.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.entity.Person
import com.github.carvaldo.fimo.api.datasource.repository.service.imdb.service.PersonService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.springframework.stereotype.Component
import retrofit2.Response
import javax.servlet.UnavailableException

@Component
class PersonUseCase(private val service: PersonService) {
    @Throws(UnavailableException::class, LimitReachedException::class)
    fun getPersonFromImdb(apiId: String): Person {
        val response = service.getProfile(ServiceGenerator.API_KEY, apiId).execute()
        return when(response.isSuccessful) { // TODO: Unificar validação da resposta.
            true -> if (response.body() == null || !response.body()!!.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                } else response.body()!!
            else -> throw UnavailableException("Serviço indisponível.")
        }
    }
}