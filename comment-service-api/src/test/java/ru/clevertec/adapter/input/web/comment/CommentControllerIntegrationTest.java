package ru.clevertec.adapter.input.web.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.adapter.input.web.comment.dto.CommentRequest;
import ru.clevertec.adapter.input.web.comment.dto.CommentResponse;
import ru.clevertec.adapter.input.web.comment.dto.CommentUpdateRequest;
import ru.clevertec.config.ApplicationNoSecurity;
import ru.clevertec.util.TestData;

import java.security.Principal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 2412)
@Import(ApplicationNoSecurity.class)
@Sql(scripts = "classpath:db/data.sql")
class CommentControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    private static final String RESPONSE_BODY = "userResponse#ok.json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldReadAllComments() throws Exception {
        //given
        int pageNumber = 0;
        int pageSize = 3;
        CommentResponse commentResponse = TestData.generateCommentResponse();
        UUID newsId = commentResponse.newsId();

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments", newsId)
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize));

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.length()").value(3),
                        jsonPath("$[2].id").value(commentResponse.id().toString()),
                        jsonPath("$[2].text").value(commentResponse.text()),
                        jsonPath("$[2].newsId").value(commentResponse.newsId().toString()),
                        jsonPath("$[2].authorId").value(commentResponse.authorId().toString())
                );
    }

    @Test
    void shouldReadCommentById_whenCommentExist() throws Exception {
        //given
        UUID newsId = UUID.fromString("44444444-4444-4444-4444-444444444444");
        UUID commentId = UUID.fromString("00000000-0000-0000-0000-000000000011");
        CommentResponse commentResponse = TestData.generateCommentResponse2();

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments/{commentId}",
                newsId, commentId);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(commentResponse.id().toString()),
                        jsonPath("$.text").value(commentResponse.text()),
                        jsonPath("$.newsId").value(commentResponse.newsId().toString()),
                        jsonPath("$.authorId").value(commentResponse.authorId().toString())
                );
    }

    @Test
    void shouldNotReadCommentById_whenCommentNotExist() throws Exception {
        //given
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID wrongCommentId = UUID.fromString("00000000-0000-0000-0000-000000000110");

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments/{commentId}",
                newsId, wrongCommentId);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateComment() throws Exception {
        //given
        CommentRequest commentRequest = TestData.generateCommentRequest();
        CommentResponse commentResponse = TestData.generateCommentResponse();
        String jsonCommentRequest = objectMapper.writeValueAsString(commentRequest);
        UUID authorId = commentResponse.authorId();
        UUID newsId = commentResponse.newsId();

        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName())
                .thenReturn(authorId.toString());

        WireMock.stubFor(WireMock.get("/api/v1/users/" + authorId)
                .willReturn(WireMock.aResponse()
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBodyFile(RESPONSE_BODY)));


        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/news/{newsId}/comments", newsId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .content(jsonCommentRequest)
                .with(request -> {
                    request.setUserPrincipal(mockPrincipal);
                    return request;
                });

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").isNotEmpty(),
                        jsonPath("$.text").value(commentResponse.text()),
                        jsonPath("$.newsId").value(commentResponse.newsId().toString()),
                        jsonPath("$.authorId").value(commentResponse.authorId().toString())
                );
    }

    @Test
    void shouldUpdateComment() throws Exception {
        //given
        CommentUpdateRequest commentUpdateRequest = TestData.generateCommentUpdateRequest();
        CommentResponse commentResponse = TestData.generateCommentResponse();
        String jsonCommentRequest = objectMapper.writeValueAsString(commentUpdateRequest);
        UUID newsId = commentResponse.newsId();

        MockHttpServletRequestBuilder requestBuilder = put("/api/v1/news/{newsId}/comments", newsId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .content(jsonCommentRequest);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").isNotEmpty(),
                        jsonPath("$.text").value(commentResponse.text()),
                        jsonPath("$.newsId").value(commentResponse.newsId().toString()),
                        jsonPath("$.authorId").value(commentResponse.authorId().toString())
                );
    }

    @Test
    void shouldDeleteCommentById() throws Exception {
        //given
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID commentId = UUID.fromString("00000000-0000-0000-0000-000000000010");

        MockHttpServletRequestBuilder requestBuilder = delete("/api/v1/news/{newsId}/comments/{commentId}",
                newsId, commentId);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAllComments() throws Exception {
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");

        MockHttpServletRequestBuilder requestBuilder = delete("/api/v1/news/{newsId}/comments",
                newsId);

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}