package com.luthfihariz.almasgocore.integration.dashboard;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.ErrorCode;
import com.luthfihariz.almasgocore.controller.v1.dto.request.RegisterUserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DashboardUserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private final String url = "/dashboard/v1/user";

    @Test
    @Sql(value = {"classpath:db/truncate_user.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void registerValidUserDataShouldReturnSuccessWithCorrectResponse() throws Exception {
        var userRequestDto = new RegisterUserRequestDto("test2@gmail.com", "12345678", "Test User");
        var content = objectMapper.writeValueAsString(userRequestDto);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userRequestDto.getName()))
                .andExpect(jsonPath("$.email").value(userRequestDto.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @Sql({"classpath:db/seed_user.sql"})
    @Sql(value = {"classpath:db/truncate_user.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void registerAlreadyRegisteredEmailShouldReturnAppropriateErrorCode() throws Exception {
        var userRequestDto = new RegisterUserRequestDto("test2@gmail.com", "12345678", "Test User");
        var content = objectMapper.writeValueAsString(userRequestDto);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.ERROR_USER_ALREADY_REGISTERED.toString()));
    }

}
