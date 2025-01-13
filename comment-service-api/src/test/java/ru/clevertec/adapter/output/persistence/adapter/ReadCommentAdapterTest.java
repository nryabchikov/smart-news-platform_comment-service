package ru.clevertec.adapter.output.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.adapter.output.persistence.jpa.PersistenceCommentMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.CommentRepository;
import ru.clevertec.exception.CommentNotFoundException;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadCommentAdapterTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    PersistenceCommentMapper commentMapper;

    @InjectMocks
    ReadCommentAdapter readCommentAdapter;

    @Test
    void shouldReadAllCommentsByNewsId() {
        //given
        UUID newsId = UUID.randomUUID();
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CommentEntity> commentEntities = TestData.generateListOfCommentEntities();
        List<CommentPortResult> commentPortResults = new ArrayList<>();

        when(commentRepository.findCommentEntitiesByNewsId(pageable, newsId))
                .thenReturn(new PageImpl<>(commentEntities, pageable, commentEntities.size()));
        for (CommentEntity commentEntity : commentEntities) {
            CommentPortResult mappedResult = TestData.generateCommentPortResult();
            when(commentMapper.toCommentPortResult(commentEntity))
                    .thenReturn(mappedResult);
            commentPortResults.add(mappedResult);
        }

        //when
        Page<CommentPortResult> actualCommentPortResult =
                readCommentAdapter.readAllCommentsByNewsId(pageable, newsId);

        //then
        assertEquals(commentPortResults, actualCommentPortResult.getContent());
        verify(commentRepository).findCommentEntitiesByNewsId(any(), any());
        verify(commentMapper, times(commentEntities.size())).toCommentPortResult(any());
    }

    @Test
    void shouldReadCommentByIdAndNewsId_whenCommentExist() {
        //given
        UUID newsId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();
        CommentEntity commentEntity = TestData.generateCommentEntity();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();

        when(commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId))
                .thenReturn(Optional.of(commentEntity));
        when(commentMapper.toCommentPortResult(commentEntity))
                .thenReturn(commentPortResult);

        //when
        CommentPortResult actualCommentPortResult = readCommentAdapter.readCommentByIdAndNewsId(commentId, newsId);

        //then
        assertEquals(commentPortResult, actualCommentPortResult);
        verify(commentRepository).findCommentEntityByIdAndNewsId(any(), any());
        verify(commentMapper).toCommentPortResult(any());
    }

    @Test
    void shouldReadCommentByIdAndNewsId_whenCommentNotExist() {
        //given
        UUID newsId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();

        doThrow(CommentNotFoundException.class)
                .when(commentRepository).findCommentEntityByIdAndNewsId(commentId, newsId);

        //when, then
        assertThrows(
                CommentNotFoundException.class,
                () -> commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId)
        );
        verify(commentRepository).findCommentEntityByIdAndNewsId(any(), any());
        verify(commentMapper, never()).toCommentPortResult(any());
    }
}