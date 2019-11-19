package com.monsterapps.monsterreaderapi

import com.monsterapps.monsterreaderapi.exception.MonsterExceptionType.TECHNICAL_EXCEPTION
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.hamcrest.Matchers.`is`

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerTest @Autowired constructor(private val mockMVC: MockMvc) {

    @Test
    fun `should handle generic exception when an invalid request is sent`() {
        // TODO: this should return a 400 status code once the exception wrapper is able to handle bad requests
        mockMVC.perform(
            post("/api/v1/books")
                .content("invalid payload")
                .contentType(APPLICATION_JSON))
            .andExpect(status().isInternalServerError)
            .andExpect(jsonPath("$.category", `is`(TECHNICAL_EXCEPTION.description)));
    }
}