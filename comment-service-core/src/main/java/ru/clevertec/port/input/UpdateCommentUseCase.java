package ru.clevertec.port.input;

import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;

import java.util.UUID;

public interface UpdateCommentUseCase {
    CommentUseCaseResult updateComment(UUID newsId, CommentUpdateCommand commentUpdateCommand);
}
