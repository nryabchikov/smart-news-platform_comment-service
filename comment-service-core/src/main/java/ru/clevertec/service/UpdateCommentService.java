package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.UpdateCommentUseCase;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.WriteCommentPort;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCommentService implements UpdateCommentUseCase {

    private final WriteCommentPort commentPort;
    private final DomainCommentMapper commentMapper;
    private final Cache<UUID, Comment> cache;

    @Override
    @Transactional
    public CommentUseCaseResult updateComment(UUID newsId, CommentUpdateCommand commentUpdateCommand) {
        log.info("UpdateCommentService.updateComment: Updating comment. newsId: {}, command: {}", newsId,
                commentUpdateCommand);
        Comment comment = commentMapper.toComment(commentUpdateCommand);
        cache.put(comment.getId(), comment);
        CommentUseCaseResult commentUseCaseResult = commentMapper.toCommentUseCaseResult(
                commentPort.updateComment(newsId, commentMapper.toCommentUpdatePortCommand(comment))
        );
        log.info("UpdateCommentService.updateComment: Comment updated successfully. Result: {}", commentUseCaseResult);
        return commentUseCaseResult;
    }
}
