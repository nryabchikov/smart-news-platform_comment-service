package ru.clevertec.adapter.input.web.comment;

import org.mapstruct.Mapper;
import ru.clevertec.adapter.input.web.comment.dto.CommentRequest;
import ru.clevertec.adapter.input.web.comment.dto.CommentResponse;
import ru.clevertec.adapter.input.web.comment.dto.CommentUpdateRequest;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;

@Mapper(componentModel = "spring")
public interface WebCommentMapper {
    CommentResponse toCommentResponse(CommentUseCaseResult commentUseCaseResult);
    CommentCreateCommand toCommentCreateCommand(CommentRequest commentRequest);
    CommentUpdateCommand toCommentUpdateCommand(CommentUpdateRequest commentUpdateRequest);
}
