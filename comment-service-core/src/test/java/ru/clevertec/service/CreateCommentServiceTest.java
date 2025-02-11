package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.client.UserClient;
import ru.clevertec.client.dto.UserResponse;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.WriteCommentPort;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCommentServiceTest {

    @Mock
    WriteCommentPort commentPort;

    @Mock
    DomainCommentMapper commentMapper;

    @Mock
    Cache<UUID, Comment> cache;

    @Mock
    UserClient userClient;

    @InjectMocks
    CreateCommentService createCommentService;

    @Test
    void shouldCreateComment() {
        //given
        UUID keycloakUserId = UUID.randomUUID();
        UUID newsId = UUID.randomUUID();
        Comment comment = TestData.generateComment();
        CommentCreateCommand commentCreateCommand = TestData.generateCommentCreateCommand();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();
        CommentCreatePortCommand commentCreatePortCommand = TestData.generateCommentCreatePortCommand();
        UserResponse userResponse = new UserResponse(keycloakUserId, UUID.randomUUID(), "Nekitos4");

        when(userClient.readUserByKeycloakId(keycloakUserId))
                .thenReturn(userResponse);
        when(commentMapper.toComment(commentCreateCommand))
                .thenReturn(comment);
        when(commentMapper.toCommentCreatePortCommand(comment))
                .thenReturn(commentCreatePortCommand);
        when(commentPort.createComment(newsId, commentCreatePortCommand))
                .thenReturn(commentPortResult);
        when(commentMapper.toCommentUseCaseResult(commentPortResult))
                .thenReturn(commentUseCaseResult);

        //when
        CommentUseCaseResult actualCommentUseCaseResult =
                createCommentService.createComment(newsId, commentCreateCommand, keycloakUserId);

        //then
        assertEquals(commentUseCaseResult, actualCommentUseCaseResult);
        verify(userClient).readUserByKeycloakId(any());
        verify(commentMapper).toComment(any(CommentCreateCommand.class));
        verify(commentMapper).toCommentCreatePortCommand(any());
        verify(commentPort).createComment(any(), any());
        verify(commentMapper).toCommentUseCaseResult(any(CommentPortResult.class));
        verify(cache).put(any(), any());
    }
}