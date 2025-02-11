package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.Comment;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;

@Mapper(componentModel = "spring")
public interface DomainCommentMapper {
    CommentCreatePortCommand toCommentCreatePortCommand(Comment comment);
    CommentUpdatePortCommand toCommentUpdatePortCommand(Comment comment);
    CommentUseCaseResult toCommentUseCaseResult(CommentPortResult commentPortResult);
    CommentUseCaseResult toCommentUseCaseResult(Comment comment);
    Comment toComment(CommentPortResult commentPortResult);
    Comment toComment(CommentCreateCommand commentCreateCommand);
    Comment toComment(CommentUpdateCommand commentUpdateCommand);
}

