package ru.clevertec.adapter.input.web.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.clevertec.adapter.input.web.comment.dto.CommentRequest;
import ru.clevertec.adapter.input.web.comment.dto.CommentResponse;
import ru.clevertec.adapter.input.web.comment.dto.CommentUpdateRequest;
import ru.clevertec.exception.CommentNotFoundException;
import ru.clevertec.port.input.CreateCommentUseCase;
import ru.clevertec.port.input.DeleteCommentUseCase;
import ru.clevertec.port.input.ReadCommentUseCase;
import ru.clevertec.port.input.UpdateCommentUseCase;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CreateCommentUseCase createCommentUseCase;

    @MockitoBean
    ReadCommentUseCase readCommentUseCase;

    @MockitoBean
    UpdateCommentUseCase updateCommentUseCase;

    @MockitoBean
    DeleteCommentUseCase deleteCommentUseCase;

    @MockitoBean
    WebCommentMapper commentMapper;

    @Test
    void shouldReadAllComments() throws Exception {
        //given
        UUID newsId = UUID.randomUUID();
        int pageNumber = 0;
        int pageSize = 3;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<CommentUseCaseResult> commentUseCaseResults = TestData.generateListOfCommentUseCaseResults();

        Page<CommentUseCaseResult> commentUseCaseResultsPage =
                new PageImpl<>(commentUseCaseResults.subList(0, 3), pageRequest, commentUseCaseResults.size());

        when(readCommentUseCase.readAllCommentsByNewsId(newsId, pageNumber, pageSize))
                .thenReturn(commentUseCaseResultsPage);

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments", newsId)
                .param("pageNumber", String.valueOf(pageNumber))
                .param("pageSize", String.valueOf(pageSize))
                .with(user("Nekitos4").roles("ADMIN"));


        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.length()").value(3)
                );
    }

    @Test
    void shouldReadCommentById_whenCommentExist() throws Exception {
        //given
        CommentResponse commentResponse = TestData.generateCommentResponse();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();
        UUID commentId = commentResponse.id();
        UUID newsId = commentResponse.newsId();

        when(readCommentUseCase.readCommentByIdAndNewsId(commentId, newsId))
                .thenReturn(commentUseCaseResult);
        when(commentMapper.toCommentResponse(commentUseCaseResult))
                .thenReturn(commentResponse);

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments/{commentsId}",
                newsId, commentId)
                .with(user("Nekitos4").roles("ADMIN"));

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        jsonPath("$.id").value(commentId.toString()),
                        jsonPath("$.text").value(commentResponse.text()),
                        jsonPath("$.newsId").value(commentResponse.newsId().toString()),
                        jsonPath("$.authorId").value(commentResponse.authorId().toString())
                );
    }

    @Test
    void shouldNotReadCommentById_whenCommentNotExist() throws Exception {
        //given
        UUID commentId = UUID.randomUUID();
        UUID newsId = UUID.randomUUID();

        when(readCommentUseCase.readCommentByIdAndNewsId(commentId, newsId))
                .thenThrow(CommentNotFoundException.class);

        MockHttpServletRequestBuilder requestBuilder = get("/api/v1/news/{newsId}/comments/{commentId}",
                newsId, commentId)
                .with(user("Nekitos4").roles("ADMIN"));

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateComment() throws Exception {
        //given
        UUID authorId = UUID.randomUUID();
        CommentRequest commentRequest = TestData.generateCommentRequest();
        CommentCreateCommand commentCreateCommand = TestData.generateCommentCreateCommand();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();
        CommentResponse commentResponse = TestData.generateCommentResponse();
        UUID newsId = commentResponse.newsId();

        when(commentMapper.toCommentCreateCommand(commentRequest))
                .thenReturn(commentCreateCommand);
        when(createCommentUseCase.createComment(newsId, commentCreateCommand, authorId))
                .thenReturn(commentUseCaseResult);
        when(commentMapper.toCommentResponse(commentUseCaseResult))
                .thenReturn(commentResponse);

        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/news/{newsId}/comments", newsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentRequest))
                .with(SecurityMockMvcRequestPostProcessors.user(new User(authorId.toString(), "",
                        new ArrayList<>())))
                .with(csrf());

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(commentResponse.id().toString()))
                .andExpect(jsonPath("$.text").value(commentResponse.text()))
                .andExpect(jsonPath("$.newsId").value(commentResponse.newsId().toString()))
                .andExpect(jsonPath("$.authorId").value(commentResponse.authorId().toString()));
    }

    @Test
    void shouldUpdateComment() throws Exception {
        //given
        CommentResponse commentResponse = TestData.generateCommentResponse();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();
        CommentUpdateCommand commentUpdateCommand = TestData.generateCommentUpdateCommand();
        CommentUpdateRequest commentUpdateRequest = TestData.generateCommentUpdateRequest();
        UUID newsId = commentResponse.newsId();

        when(commentMapper.toCommentUpdateCommand(commentUpdateRequest))
                .thenReturn(commentUpdateCommand);
        when(updateCommentUseCase.updateComment(newsId, commentUpdateCommand))
                .thenReturn(commentUseCaseResult);
        when(commentMapper.toCommentResponse(commentUseCaseResult))
                .thenReturn(commentResponse);


        MockHttpServletRequestBuilder requestBuilder = put("/api/v1/news/{newsId}/comments", newsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentUpdateRequest))
                .with(user("Nekitos4").roles("ADMIN"))
                .with(csrf());

        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(commentResponse.id().toString()))
                .andExpect(jsonPath("$.text").value(commentResponse.text()))
                .andExpect(jsonPath("$.newsId").value(commentResponse.newsId().toString()))
                .andExpect(jsonPath("$.authorId").value(commentResponse.authorId().toString()));
    }

    @Test
    void shouldDeleteCommentById() throws Exception {
        //given
        UUID commentId = UUID.randomUUID();
        UUID newsId = UUID.randomUUID();

        MockHttpServletRequestBuilder requestBuilder =
                delete("/api/v1/news/{id}/comments/{commentId}", newsId, commentId)
                        .with(user("Nekitos4").roles("ADMIN"))
                        .with(csrf());
        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteAllComments() throws Exception {
        //given
        UUID newsId = UUID.randomUUID();

        MockHttpServletRequestBuilder requestBuilder =
                delete("/api/v1/news/{id}/comments", newsId)
                        .with(user("Nekitos4").roles("ADMIN"))
                        .with(csrf());
        //when, then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}