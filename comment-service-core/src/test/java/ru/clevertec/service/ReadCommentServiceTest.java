package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.ReadCommentPort;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadCommentServiceTest {

    @Mock
    ReadCommentPort commentPort;

    @Mock
    DomainCommentMapper commentMapper;

    @Mock
    Cache<UUID , Comment> cache;

    @InjectMocks
    ReadCommentService readCommentService;

    @Test
    void shouldReadAllCommentsByNewsId() {
        //given
        int pageNumber = 0;
        int pageSize = 3;
        UUID newsId = UUID.randomUUID();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CommentPortResult> commentPortResults = TestData.generateListOfCommentPortResults();
        List<CommentUseCaseResult> commentUseCaseResults = new ArrayList<>();

        when(commentPort.readAllCommentsByNewsId(pageable, newsId))
                .thenReturn(new PageImpl<>(commentPortResults, pageable, commentPortResults.size()));
        for (CommentPortResult commentPortResult : commentPortResults) {
            CommentUseCaseResult mappedResult = TestData.generateCommentUseCaseResult();
            when(commentMapper.toCommentUseCaseResult(commentPortResult))
                    .thenReturn(mappedResult);
            commentUseCaseResults.add(mappedResult);
        }

        //when
        Page<CommentUseCaseResult> actualCommentUseCaseResults =
                readCommentService.readAllCommentsByNewsId(newsId, pageNumber, pageSize);

        //then
        assertEquals(commentUseCaseResults, actualCommentUseCaseResults.getContent());
        verify(commentPort).readAllCommentsByNewsId(pageable, newsId);
        verify(commentMapper, times(commentPortResults.size())).toCommentUseCaseResult(any(CommentPortResult.class));
    }

    @Test
    void shouldReadCommentByIdAndNewsId_whenCommentExistInCache() {
        //given
        UUID commentId = UUID.randomUUID();
        UUID newsId = UUID.randomUUID();
        Comment comment = TestData.generateComment();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();

        when(cache.get(commentId))
                .thenReturn(Optional.of(comment));
        when(commentMapper.toCommentUseCaseResult(comment))
               .thenReturn(commentUseCaseResult);

        //when
        CommentUseCaseResult actualCommentUseCaseResult =
                readCommentService.readCommentByIdAndNewsId(commentId, newsId);

        //then
        assertEquals(commentUseCaseResult, actualCommentUseCaseResult);
        verify(cache).get(any());
        verify(commentMapper).toCommentUseCaseResult(any(Comment.class));
        verify(cache, never()).put(any(), any());
        verify(commentPort, never()).readAllCommentsByNewsId(any(), any());
    }

    @Test
    void shouldReadCommentByIdAndNewsId_whenCommentNotExistInCache() {
        //given
        UUID commentId = UUID.randomUUID();
        UUID newsId = UUID.randomUUID();
        Comment comment = TestData.generateComment();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();
        CommentUseCaseResult commentUseCaseResult = TestData.generateCommentUseCaseResult();

        when(cache.get(commentId))
                .thenReturn(Optional.empty());
        when(commentPort.readCommentByIdAndNewsId(commentId, newsId))
                .thenReturn(commentPortResult);
        when(commentMapper.toComment(commentPortResult))
                .thenReturn(comment);
        when(commentMapper.toCommentUseCaseResult(comment))
                .thenReturn(commentUseCaseResult);

        //when
        CommentUseCaseResult actualCommentUseCaseResult =
                readCommentService.readCommentByIdAndNewsId(commentId, newsId);

        //then
        assertEquals(commentUseCaseResult, actualCommentUseCaseResult);
        verify(cache).get(any());
        verify(commentPort).readCommentByIdAndNewsId(any(), any());
        verify(commentMapper).toComment(any(CommentPortResult.class));
        verify(commentMapper).toCommentUseCaseResult(any(Comment.class));
        verify(cache).put(any(), any());
    }
}