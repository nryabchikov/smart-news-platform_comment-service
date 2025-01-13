package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.client.UserClient;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.CreateCommentUseCase;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.WriteCommentPort;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCommentService implements CreateCommentUseCase {

    private final WriteCommentPort commentPort;
    private final DomainCommentMapper commentMapper;
    private final Cache<UUID, Comment> cache;
    private final UserClient userClient;

    @Override
    @Transactional
    public CommentUseCaseResult createComment(UUID newsId, CommentCreateCommand commentCreateCommand, UUID keycloakUserId) {
        log.info("CreateCommentService.createComment: Creating comment. newsId: {}, command: {}, keycloakUserId: {}", newsId, commentCreateCommand, keycloakUserId);
        UUID authorId = userClient.readUserByKeycloakId(keycloakUserId).id();
        Comment comment = commentMapper.toComment(commentCreateCommand);
        comment.setId(UUID.randomUUID());
        comment.setAuthorId(authorId);
        cache.put(comment.getId(), comment);
        CommentUseCaseResult commentUseCaseResult = commentMapper.toCommentUseCaseResult(
                commentPort.createComment(newsId, commentMapper.toCommentCreatePortCommand(comment))
        );
        log.info("CreateCommentService.createComment: Comment created successfully. Result: {}",
                commentUseCaseResult);
        return commentUseCaseResult;
    }
}
