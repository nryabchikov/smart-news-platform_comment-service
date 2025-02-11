package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.WriteCommentPort;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;
import ru.clevertec.util.TestData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCommentServiceTest {

    @Mock
    WriteCommentPort commentPort;

    @Mock
    DomainCommentMapper commentMapper;

    @Mock
    Cache<UUID, Comment> cache;

    @InjectMocks
    UpdateCommentService updateCommentService;

    @Test
    void shouldUpdateComment() {
        //given
        UUID newsId = UUID.randomUUID();
        Comment comment = TestData.generateComment();
        CommentUpdateCommand commentUpdateCommand = TestData.generateCommentUpdateCommand();
        CommentUpdatePortCommand commentUpdatePortCommand = TestData.generateCommentUpdatePortCommand();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();

        when(commentMapper.toComment(commentUpdateCommand))
                .thenReturn(comment);
        when(commentMapper.toCommentUpdatePortCommand(comment))
                .thenReturn(commentUpdatePortCommand);
        when(commentPort.updateComment(newsId, commentUpdatePortCommand))
                .thenReturn(commentPortResult);
        when(commentMapper.toCommentUseCaseResult(commentPortResult))
                .thenReturn(commentUseCaseResult);

        //when
        CommentUseCaseResult actualCommentUseCaseResult = updateCommentService.updateComment(newsId, commentUpdateCommand);

        //then
        assertEquals(commentUseCaseResult, actualCommentUseCaseResult);
        verify(commentMapper).toComment(any(CommentUpdateCommand.class));
        verify(commentMapper).toCommentUpdatePortCommand(any());
        verify(commentPort).updateComment(any(), any());
        verify(commentMapper).toCommentUseCaseResult(any(CommentPortResult.class));
        verify(cache).put(any(), any());
    }
}