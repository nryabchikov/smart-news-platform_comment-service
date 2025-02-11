package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.port.input.DeleteCommentUseCase;
import ru.clevertec.port.output.WriteCommentPort;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCommentService implements DeleteCommentUseCase {

    private final WriteCommentPort commentPort;
    private final Cache<UUID, Comment> cache;

    @Override
    @Transactional
    public void deleteComment(UUID newsId, UUID commentId) {
        log.info("DeleteCommentService.deleteComment: Deleting comment. newsId: {}, commentId: {}"
                , newsId, commentId);
        commentPort.deleteComment(newsId, commentId);
        cache.delete(commentId);
        log.info("DeleteCommentService.deleteComment: Comment deleted successfully.");
    }

    @Override
    @Transactional
    public void deleteAllComments(UUID newsId) {
        log.info("DeleteCommentService.deleteAllComments: Deleting comments. newsId: {}"
                , newsId);
        List<UUID> commentIds = commentPort.deleteAllComments(newsId);
        for (UUID commentId: commentIds) {
            cache.delete(commentId);
        }
        log.info("DeleteCommentService.deleteAllComments: Comments deleted successfully.");
    }
}
