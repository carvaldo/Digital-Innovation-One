package com.github.carvaldo.fimo.api

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= [FimoApiApplication::class])
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-dev-test.properties"])
class MovieTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @ParameterizedTest
    @ValueSource(strings = ["Titanic"])
    fun isSuccessfullSearchMoviesByName(name: String) {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/movies/search/{name}", name)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].remoteId").isNotEmpty)
    }

    @ParameterizedTest
    @ValueSource(strings = ["tt0120338"])
    fun isSuccessfullGetMovieDetail(id: String) {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/movies/detail/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.apiId").value(id))
    }
}
