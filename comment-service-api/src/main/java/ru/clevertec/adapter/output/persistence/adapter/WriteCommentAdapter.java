package ru.clevertec.adapter.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.clevertec.adapter.output.persistence.jpa.PersistenceCommentMapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.adapter.output.persistence.jpa.repository.CommentRepository;
import ru.clevertec.exception.CommentNotFoundException;
import ru.clevertec.port.output.WriteCommentPort;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class WriteCommentAdapter implements WriteCommentPort {

    private final CommentRepository commentRepository;
    private final PersistenceCommentMapper commentMapper;

    @Override
    public CommentPortResult createComment(UUID newsId, CommentCreatePortCommand commentCreatePortCommand) {
        log.info("WriteCommentAdapter.createComment: Creating comment. newsId: {}, command: {}"
                , newsId, commentCreatePortCommand);
        CommentEntity commentEntity = commentMapper.toCommentEntity(commentCreatePortCommand);
        commentEntity.setNewsId(newsId);
        CommentPortResult commentPortResult = commentMapper.toCommentPortResult(commentRepository.save(commentEntity));
        log.debug("WriteCommentAdapter.createComment: Comment created successfully. Result: {}", commentPortResult);
        return commentPortResult;
    }

    @Override
    public CommentPortResult updateComment(UUID newsID, CommentUpdatePortCommand commentUpdatePortCommand) {
        log.info("WriteCommentAdapter.updateComment: Updating comment. newsId: {}, command: {}", newsID,
                commentUpdatePortCommand);
        CommentEntity commentEntity =
                commentRepository.findCommentEntityByIdAndNewsId(commentUpdatePortCommand.id(), newsID)
                        .orElseThrow(() -> {
                            log.error("WriteCommentAdapter.updateComment: Comment not found. commentId: {}," +
                                    " newsId: {}", commentUpdatePortCommand.id(), newsID);
                            return CommentNotFoundException.byId(commentUpdatePortCommand.id());
                        });

        commentEntity.setId(commentUpdatePortCommand.id());
        commentEntity.setText(commentUpdatePortCommand.text());
        commentEntity.setTime(commentUpdatePortCommand.time());

        CommentPortResult commentPortResult = commentMapper.toCommentPortResult(commentEntity);
        log.debug("WriteCommentAdapter.updateComment: Comment updated successfully. Result: {}", commentPortResult);
        return commentPortResult;
    }

    @Override
    public void deleteComment(UUID newsId, UUID commentId) {
        log.info("WriteCommentAdapter.deleteComment: Deleting comment. newsId: {}, commentId: {}", newsId, commentId);
        commentRepository.deleteByIdAndNewsId(commentId, newsId);
        log.debug("WriteCommentAdapter.deleteComment: Comment deleted successfully.");
    }

    @Override
    public List<UUID> deleteAllComments(UUID newsId) {
        log.info("WriteCommentAdapter.deleteAllComments: Deleting all comments. newsId: {}", newsId);
        List<UUID> commentIds = new ArrayList<>();
        List<CommentEntity> commentEntities = commentRepository.findCommentEntitiesByNewsId(newsId);
        for (CommentEntity commentEntity: commentEntities) {
            UUID commentId = commentEntity.getId();
            commentRepository.deleteByIdAndNewsId(commentId, newsId);
            commentIds.add(commentId);
        }
        log.debug("WriteCommentAdapter.deleteAllComments: All comments deleted successfully.");
        return commentIds;
    }
}
