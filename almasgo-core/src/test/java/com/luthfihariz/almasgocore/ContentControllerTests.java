package com.luthfihariz.almasgocore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class ContentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSingleContentShouldReturnSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/content/1285"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
