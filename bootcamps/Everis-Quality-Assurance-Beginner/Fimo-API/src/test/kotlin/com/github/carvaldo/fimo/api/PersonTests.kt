package com.github.carvaldo.fimo.api

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= [FimoApiApplication::class])
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-dev-test.properties"])
class PersonTests {
    private lateinit var mockMvc: MockMvc

    @ParameterizedTest
    @ValueSource(strings = ["nm0000154"])
    fun isSuccessfullGetPersonDetail(id: String) {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/persons/profile/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data.apiId").value(id))
    }
}
