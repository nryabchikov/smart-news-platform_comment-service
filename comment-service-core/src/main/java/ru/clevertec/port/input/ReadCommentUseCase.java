package ru.clevertec.port.input;

import org.springframework.data.domain.Page;
import ru.clevertec.port.input.command.CommentUseCaseResult;

import java.util.UUID;

public interface ReadCommentUseCase {
    Page<CommentUseCaseResult> readAllCommentsByNewsId(UUID newsId, int pageNumber, int pageSize);
    CommentUseCaseResult readCommentByIdAndNewsId(UUID commentId, UUID newsId);
}
