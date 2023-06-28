package com.example.driverservice.controller;

import com.example.driverservice.exception.BadArgumentException;
import com.example.driverservice.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showBalanceTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/account/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value("99"))
                .andExpect(jsonPath("$.redDollarBalance").value("0.0"))
                .andExpect(jsonPath("$.greenDollarBalance").value("0.0"));

    }

    @Test
    void showBalanceTestNotFoundError() throws Exception {
        this.mockMvc.perform(get("/api/v1/account/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void addRedDollarTest() throws Exception {

        String json = """
                {
                    "red_dollar_balance": "2.00"
                }
                """;

        this.mockMvc.perform(put("/api/v1/account/99/red/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/account/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.redDollarBalance").value("2.0"))
                .andExpect(jsonPath("$.blueDollarBalance").value("3.0"));
    }

    @Test
    void addRedDollarTestNegativeNumberError() throws Exception {

        String json = """
                {
                    "red_dollar_balance": "-2.00"
                }
                """;

        this.mockMvc.perform(put("/api/v1/account/99/red/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException));
    }

    @Test
    void addRedDollarTestNotFoundAccountError() throws Exception {

        String json = """
                {
                    "red_dollar_balance": "2.00"
                }
                """;

        this.mockMvc.perform(put("/api/v1/account/23/red/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void subRedDollarTest() throws Exception {

        String json = """
                {
                    "red_dollar_balance": "2.00"
                }
                """;

        this.mockMvc.perform(put("/api/v1/account/100/red/sub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/account/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.redDollarBalance").value("1.0"))
                .andExpect(jsonPath("$.blueDollarBalance").value("1.5"));
    }
}