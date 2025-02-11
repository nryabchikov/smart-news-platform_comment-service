package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.clevertec.cache.Cache;
import ru.clevertec.domain.Comment;
import ru.clevertec.mapper.DomainCommentMapper;
import ru.clevertec.port.input.ReadCommentUseCase;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.ReadCommentPort;
import ru.clevertec.port.output.port.CommentPortResult;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadCommentService implements ReadCommentUseCase {

    private final ReadCommentPort commentPort;
    private final DomainCommentMapper commentMapper;
    private final Cache<UUID, Comment> cache;

    @Override
    public Page<CommentUseCaseResult> readAllCommentsByNewsId(UUID newsId, int pageNumber, int pageSize) {
        log.info("ReadCommentService.readAllCommentsByNewsId: Reading comments. newsId: {}, pageNumber: {}," +
                " pageSize: {}", newsId, pageNumber, pageSize);
        Page<CommentUseCaseResult> commentUseCaseResults = commentPort.readAllCommentsByNewsId(
                        PageRequest.of(pageNumber, pageSize), newsId)
                .map(commentMapper::toCommentUseCaseResult);
        log.info("ReadCommentService.readAllCommentsByNewsId: Comments read successfully. Count: {}",
                commentUseCaseResults.getTotalElements());
        return commentUseCaseResults;
    }

    @Override
    public CommentUseCaseResult readCommentByIdAndNewsId(UUID commentId, UUID newsId) {
        log.info("ReadCommentService.readCommentByIdAndNewsId: Reading comment. commentId: {}, newsId: {}",
                commentId, newsId);
        CommentUseCaseResult commentUseCaseResult = cache.get(commentId)
                .map(commentMapper::toCommentUseCaseResult)
                .orElseGet(() -> {
                    CommentPortResult commentPortResult =
                            commentPort.readCommentByIdAndNewsId(commentId, newsId);
                    Comment comment = commentMapper.toComment(commentPortResult);
                    cache.put(commentId, comment);
                    return commentMapper.toCommentUseCaseResult(comment);
                });
        log.info("ReadCommentService.readCommentByIdAndNewsId: Comment read successfully. Result: {}",
                commentUseCaseResult);
        return commentUseCaseResult;
    }
}
