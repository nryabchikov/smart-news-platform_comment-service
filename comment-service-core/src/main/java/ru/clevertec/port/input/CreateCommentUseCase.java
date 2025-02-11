package ru.clevertec.port.input;

import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;

import java.util.UUID;

public interface CreateCommentUseCase {
    CommentUseCaseResult createComment(UUID newsId, CommentCreateCommand commentCreateCommand, UUID keycloakUserId);
}
