package ru.clevertec.port.input;

import java.util.UUID;

public interface DeleteCommentUseCase {
    void deleteComment(UUID newsId, UUID commentId);
    void deleteAllComments(UUID newsId);
}
