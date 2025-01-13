package ru.clevertec.adapter.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.clevertec.adapter.output.persistence.jpa.PersistenceCommentMapper;
import ru.clevertec.adapter.output.persistence.jpa.repository.CommentRepository;
import ru.clevertec.exception.CommentNotFoundException;
import ru.clevertec.port.output.ReadCommentPort;
import ru.clevertec.port.output.port.CommentPortResult;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadCommentAdapter implements ReadCommentPort {

    private final CommentRepository commentRepository;
    private final PersistenceCommentMapper commentMapper;

    @Override
    public Page<CommentPortResult> readAllCommentsByNewsId(Pageable pageable, UUID newsId) {
        log.info("ReadCommentAdapter.readAllCommentsByNewsId: Reading comments for newsId: {}, pageable: {}",
                newsId, pageable);
        Page<CommentPortResult> commentPortResults = commentRepository.findCommentEntitiesByNewsId(pageable, newsId)
                .map(commentMapper::toCommentPortResult);
        log.debug("ReadCommentAdapter.readAllCommentsByNewsId: Comments read successfully. Count: {}",
                commentPortResults.getTotalElements());
        return commentPortResults;
    }

    @Override
    public CommentPortResult readCommentByIdAndNewsId(UUID commentId, UUID newsId) {
        log.info("ReadCommentAdapter.readCommentByIdAndNewsId: Reading comment. commentId: {}, newsId: {}",
                commentId, newsId);
        CommentPortResult commentPortResult = commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId)
                .map(commentMapper::toCommentPortResult)
                .orElseThrow(() -> {
                    log.error("ReadCommentAdapter.readCommentByIdAndNewsId: Comment not found. commentId: {}," +
                            " newsId: {}", commentId, newsId);
                    return CommentNotFoundException.byId(commentId);
                });
        log.debug("ReadCommentAdapter.readCommentByIdAndNewsId: Comment read successfully. Result: {}",
                commentPortResult);
        return commentPortResult;
    }
}
