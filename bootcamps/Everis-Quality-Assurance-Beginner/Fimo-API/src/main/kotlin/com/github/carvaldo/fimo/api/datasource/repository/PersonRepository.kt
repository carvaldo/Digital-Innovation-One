package com.github.carvaldo.fimo.api.datasource.repository

import com.github.carvaldo.fimo.api.datasource.repository.firstparty.dao.PersonDao
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.Person
import com.github.carvaldo.fimo.api.datasource.repository.firstparty.entity.transform
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.ServiceGenerator
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.entity.Person as PersonImdb
import com.github.carvaldo.fimo.api.datasource.repository.thirdpaty.imdb.service.PersonService
import com.github.carvaldo.fimo.api.exception.LimitReachedException
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import retrofit2.Response
import javax.servlet.UnavailableException

@Service
class PersonRepository constructor(
    private val personDao: PersonDao,
    private val personService: PersonService
){
    @Autowired
    private lateinit var logger: Logger

    fun getProfile(apiId: String): Person? {
        var person: Person? = personDao.findPersonByApiId(apiId)
        if (person == null) {
            val response = getPersonFromImdbAPI(apiId)
            if (response.isSuccessful) { // TODO: Unificar validação da resposta.
                val body = response.body()
                if (body == null || !body.errorMessage.isNullOrEmpty()) { // Limite gratuito atingido
                    throw LimitReachedException(ServiceGenerator.PRINCING_URL_IMDB_API)
                } else {
                    person = body.transform().let { personDao.save(it) }
                }
            } else {
                throw UnavailableException("Serviço indisponível.")
            }
        }
        return person
    }

    // TODO: Buscar e persistir filmes feitos.
    // TODO: Criar e padronizar método intermediário chamado searchExternalData(String), que consumirá dados de diferentes fontes.
    private fun getPersonFromImdbAPI(apiId: String): Response<PersonImdb> {
        return personService.getProfile(ServiceGenerator.API_KEY, apiId).execute()
    }

    private fun toProcessCast() {
        /** TODO: 12/08/2021
         * PASSO 1: O filme já foi persistido?
         *      CASO NÃO: Buscar e persistir.
         * PASSO 2: Persistir cast/relacionamento
         */
    }
}