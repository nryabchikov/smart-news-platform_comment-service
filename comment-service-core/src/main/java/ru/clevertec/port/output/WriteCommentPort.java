package ru.clevertec.port.output;

import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;

import java.util.List;
import java.util.UUID;

public interface WriteCommentPort {
    CommentPortResult createComment(UUID newsId, CommentCreatePortCommand commentCreatePortCommand);
    CommentPortResult updateComment(UUID newsID, CommentUpdatePortCommand commentUpdatePortCommand);
    void deleteComment(UUID newsId, UUID commentId);
    List<UUID> deleteAllComments(UUID newsId);
}
