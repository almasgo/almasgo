package com.luthfihariz.almasgocore.integration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.request.SortOrder;
import com.luthfihariz.almasgocore.controller.dto.request.SortRequestDto;
import com.luthfihariz.almasgocore.controller.v1.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.v1.dto.request.SearchRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String url = "/api/v1/content";

    @BeforeAll

    static void beforeAll() {

    }

    @Test
    @Order(1)
    @Sql("classpath:db/seeder.sql")
    void addContentShouldReturnSuccess() throws Exception {
        var contentRequestDto = generateContentRequestStub();

        var content = objectMapper.writeValueAsString(contentRequestDto);
        mockMvc.perform(post(url)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueExternalId").value(contentRequestDto.getExternalUniqueId()))
                .andExpect(jsonPath("$.title").value(contentRequestDto.getTitle()))
                .andExpect(jsonPath("$.description").value(contentRequestDto.getDescription()))
                .andExpect(jsonPath("$.visibility").value(contentRequestDto.getVisibility()))
                .andExpect(jsonPath("$.tags").isArray())
                .andExpect(jsonPath("$.tags[0]").value("test"))
                .andExpect(jsonPath("$.tags[1]").value("integration"))
                .andExpect(jsonPath("$.attributes").isMap())
                .andExpect(jsonPath("$.attributes.test").value("integration"));
    }

    @Test
    @Order(2)
    void getContentShouldReturnAppropriateData() throws Exception {
        var contentRequestStub = generateContentRequestStub();

        mockMvc.perform(get(url + "/1").contentType(MediaType.APPLICATION_JSON)
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueExternalId").value(contentRequestStub.getExternalUniqueId()))
                .andExpect(jsonPath("$.title").value(contentRequestStub.getTitle()))
                .andExpect(jsonPath("$.description").value(contentRequestStub.getDescription()))
                .andExpect(jsonPath("$.visibility").value(contentRequestStub.getVisibility()))
                .andExpect(jsonPath("$.tags").isArray())
                .andExpect(jsonPath("$.tags[0]").value("test"))
                .andExpect(jsonPath("$.tags[1]").value("integration"))
                .andExpect(jsonPath("$.attributes").isMap())
                .andExpect(jsonPath("$.attributes.test").value("integration"));
    }


    @Test
    @Order(3)
    void updateContentShouldReturnAppropriateData() throws Exception {
        var contentRequestDto = new ContentRequestDto(
                null,
                "Test update title",
                "Test update description",
                null,
                Arrays.asList("type", "test-integration"),
                null
        );

        mockMvc.perform(put(url + "/1")
                .content(objectMapper.writeValueAsString(contentRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueExternalId").value("abc123"))
                .andExpect(jsonPath("$.title").value(contentRequestDto.getTitle()))
                .andExpect(jsonPath("$.description").value(contentRequestDto.getDescription()))
                .andExpect(jsonPath("$.visibility").value(1))
                .andExpect(jsonPath("$.tags").isArray())
                .andExpect(jsonPath("$.tags[0]").value("type"))
                .andExpect(jsonPath("$.tags[1]").value("test-integration"))
                .andExpect(jsonPath("$.attributes").isMap())
                .andExpect(jsonPath("$.attributes.test").value("integration"));
    }

    @Test
    @Order(4)
    void deleteContentShouldReturn204() throws Exception {
        mockMvc.perform(delete(url + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(status().is(204));
    }


    private ContentRequestDto generateContentRequestStub() {
        return new ContentRequestDto(
                "abc123",
                "Test title",
                "Test description",
                1,
                Arrays.asList("test", "integration"),
                Map.of("test", "integration")
        );
    }

    @Test
    @Order(5)
    void addBulkContentsWithCorrectFormatShouldReturnSuccess() throws Exception {
        var resource = new ClassPathResource("content_bulk.csv");
        var file = new MockMultipartFile("file",
                resource.getFilename(),
                "text/csv",
                resource.getInputStream());
        mockMvc.perform(multipart(url + "/bulk")
                .file(file)
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(19))
                .andExpect(jsonPath("$.fail").value(0))
                .andExpect(jsonPath("$.total").value(19));

    }

    @Test
    @Order(6)
    void searchContentShouldSuccessAndReturnAppropriateData() throws Exception {
        var searchRequestDto = new SearchRequestDto(
                0,
                3,
                "china",
                null,
                new SortRequestDto("attributes.category", SortOrder.ASCENDING));
        mockMvc.perform(post(url + "/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchRequestDto))
                .header("api-key", "9a09107b54e3040dd2c98b2a5236f27c27013d0e18889be4368cb61bd28586d9")
                .header("engine-id", 1))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(3))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].content").isMap())
                .andExpect(jsonPath("$.results[0].content.attributes.category").value("health"));
    }
}
