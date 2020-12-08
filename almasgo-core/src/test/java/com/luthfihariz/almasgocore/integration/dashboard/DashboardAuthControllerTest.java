package com.luthfihariz.almasgocore.integration.dashboard;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.request.AuthRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DashboardAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String url = "/dashboard/v1/auth/";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({"classpath:db/user.sql"})
    public void registerCorrectUserInfoShouldReturnSuccess() throws Exception {
        var loginCredential = new AuthRequestDto("test@gmail.com", "12345678");
        var content = objectMapper.writeValueAsString(loginCredential);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
    }


    @Test
    public void authWithWrongCredentialShouldReturnUnauthorized() throws Exception {
        var loginCredential = new AuthRequestDto("test@gmail.com", "123456");
        var content = objectMapper.writeValueAsString(loginCredential);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().is4xxClientError());

    }

}
