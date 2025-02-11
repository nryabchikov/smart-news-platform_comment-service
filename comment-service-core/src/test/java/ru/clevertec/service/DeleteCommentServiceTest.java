package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.port.output.WriteCommentPort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCommentServiceTest {

    @Mock
    WriteCommentPort commentPort;

    @Mock
    Cache<UUID, Comment> cache;

    @InjectMocks
    DeleteCommentService deleteCommentService;

    @Test
    void shouldDeleteCommentById() {
        //given
        UUID newsId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();

        //when
        deleteCommentService.deleteComment(newsId, commentId);

        //then
        verify(commentPort).deleteComment(newsId, commentId);
        verify(cache).delete(commentId);
        verifyNoMoreInteractions(commentPort);
    }

    @Test
    void shouldDeleteAllComments() {
        //given
        UUID newsId = UUID.randomUUID();
        List<UUID> commentIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        when(commentPort.deleteAllComments(newsId))
                .thenReturn(commentIds);

        // when
        deleteCommentService.deleteAllComments(newsId);

        // then
        verify(commentPort).deleteAllComments(newsId);
        verify(cache, times(2)).delete(any());
    }
}