package ru.clevertec.adapter.output.persistence.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.adapter.output.persistence.jpa.PersistenceCommentMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.CommentRepository;
import ru.clevertec.exception.CommentNotFoundException;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;
import ru.clevertec.util.TestData;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WriteCommentAdapterTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    PersistenceCommentMapper commentMapper;

    @InjectMocks
    WriteCommentAdapter writeCommentAdapter;

    @Test
    void shouldCreateComment() {
        //given
        UUID newsId = UUID.randomUUID();
        CommentEntity commentEntity = TestData.generateCommentEntity();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();
        CommentCreatePortCommand commentCreatePortCommand = TestData.generateCommentCreatePortCommand();

        when(commentMapper.toCommentEntity(commentCreatePortCommand))
                .thenReturn(commentEntity);
        when(commentRepository.save(commentEntity))
                .thenReturn(commentEntity);
        when(commentMapper.toCommentPortResult(commentEntity))
                .thenReturn(commentPortResult);

        //when
        CommentPortResult actualCommentPortResult = writeCommentAdapter.createComment(newsId, commentCreatePortCommand);

        //then
        assertEquals(commentPortResult, actualCommentPortResult);
        verify(commentMapper).toCommentEntity(any());
        verify(commentRepository).save(any());
        verify(commentMapper).toCommentPortResult(any());
    }

    @Test
    void shouldUpdateComment_whenCommentExist() {
        //given
        UUID newsId = UUID.randomUUID();
        CommentEntity commentEntity = TestData.generateCommentEntity();
        CommentPortResult commentPortResult = TestData.generateCommentPortResult();
        CommentUpdatePortCommand commentUpdatePortCommand = TestData.generateCommentUpdatePortCommand();

        when(commentRepository.findCommentEntityByIdAndNewsId(commentUpdatePortCommand.id(), newsId))
                .thenReturn(Optional.of(commentEntity));
        when(commentMapper.toCommentPortResult(commentEntity))
                .thenReturn(commentPortResult);

        //when
        CommentPortResult actualCommentPortResult = writeCommentAdapter.updateComment(newsId, commentUpdatePortCommand);

        //then
        assertEquals(commentPortResult, actualCommentPortResult);
        verify(commentRepository).findCommentEntityByIdAndNewsId(any(), any());
        verify(commentMapper).toCommentPortResult(any());
    }

    @Test
    void shouldNotUpdateComment_whenCommentNotExist() {
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

    @Test
    void deleteComment() {
        //given
        UUID newsId = UUID.randomUUID();
        UUID commentId = UUID.randomUUID();

        //when
        writeCommentAdapter.deleteComment(newsId, commentId);

        //then
        verify(commentRepository).deleteByIdAndNewsId(commentId,  newsId);
        verifyNoMoreInteractions(commentRepository);
    }
}