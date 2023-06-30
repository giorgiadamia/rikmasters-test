package com.example.carservice.controller;

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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCarTest() throws Exception {

        String json = """
                {
                    "vin": "VIN123TEST",
                    "stateNumber": "e777kx77",
                    "details": [
                        {
                            "serialNumber": "74742"
                        }
                    ]
                }
                """;

        this.mockMvc.perform(post("/api/v2/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Car successfully created"))
                .andExpect(status().isOk());
    }

    @Test
    void createCarTestNoVinError() throws Exception {

        String json = """
                {
                    "stateNumber": "e777kx77",
                    "details": [
                        {
                            "serialNumber": "74742"
                        }
                    ]
                }
                """;

        this.mockMvc.perform(post("/api/v2/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCarTestBlankStateNumberError() throws Exception {

        String json = """
                {
                    "vin": "VIN123TEST",
                    "stateNumber": "",
                    "details": [
                        {
                            "serialNumber": "74742"
                        }
                    ]
                }
                """;

        this.mockMvc.perform(post("/api/v2/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCarTest() throws Exception {
        this.mockMvc.perform(get("/api/v2/car/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Successfully get car by id"))
                .andExpect(jsonPath("$.object.id").value("99"))
                .andExpect(status().isOk());
    }

    @Test
    void getCarTestNotFoundError() throws Exception {
        this.mockMvc.perform(get("/api/v2/car/22")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("There is no car with this id"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateCarTest() throws Exception {

        String json = """
                {
                    "vin": "VIN777TEST"
                }
                """;

        this.mockMvc.perform(put("/api/v2/car/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Successfully update car"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v2/car/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.object.vin").value("VIN777TEST"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCarTest() throws Exception {
        this.mockMvc.perform(delete("/api/v2/car/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.message").value("Successfully delete car"))
                .andExpect(status().isOk());
    }
}