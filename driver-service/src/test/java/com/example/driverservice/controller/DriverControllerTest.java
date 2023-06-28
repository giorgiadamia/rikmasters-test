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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createDriverTest() throws Exception {

        String json = """
                {
                    "name": "user",
                    "passport": "user1234",
                    "category": "B",
                    "birthday": "2022-06-27",
                    "experience": "2"
                }
                """;

        this.mockMvc.perform(post("/api/v1/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createDriverTestNoCategoryError() throws Exception {

        String json = """
                {
                    "name": "user",
                    "passport": "userPassport",
                    "birthday": "2022-06-27",
                    "experience": "2"
                }
                """;

        this.mockMvc.perform(post("/api/v1/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void createDriverTestExistingPassportError() throws Exception {

        String json = """
                {
                    "name": "user",
                    "passport": "passport",
                    "category": "B",
                    "birthday": "2022-06-27",
                    "experience": "2"
                }
                """;

        this.mockMvc.perform(post("/api/v1/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException));
    }

    @Test
    void getDriverTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value("99"))
                .andExpect(jsonPath("$.name").value("test"));

    }

    @Test
    void getDriverTestNotFoundError() throws Exception {
        this.mockMvc.perform(get("/api/v1/driver/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void updateDriverTest() throws Exception {

        String json = """
                {
                    "name": "userTest",
                    "passport": "passportTest",
                    "experience": "6"
                }
                """;

        this.mockMvc.perform(put("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.id").value("99"))
                .andExpect(jsonPath("$.name").value("userTest"))
                .andExpect(jsonPath("$.category").value("B"))
                .andExpect(jsonPath("$.experience").value("6"));
    }

    @Test
    void updateDriverTestDriverNotFoundError() throws Exception {

        String json = """
                {
                    "name": "newUserTest",
                    "passport": "newPassportTest",
                    "experience": "7"
                }
                """;

        this.mockMvc.perform(put("/api/v1/driver/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));

    }

    @Test
    void updateDriverTestExistingPassportError() throws Exception {

        String json = """
                {
                    "passport": "passport"
                }
                """;

        this.mockMvc.perform(put("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException));
    }

    @Test
    void deleteDriverTest() throws Exception {
        this.mockMvc.perform(delete("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/driver/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void deleteDriverTestNotFoundError() throws Exception {
        this.mockMvc.perform(get("/api/v1/driver/9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }
}