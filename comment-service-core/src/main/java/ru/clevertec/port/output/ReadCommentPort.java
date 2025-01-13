package ru.clevertec.port.output;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.port.output.port.CommentPortResult;

import java.util.UUID;

public interface ReadCommentPort {
    Page<CommentPortResult> readAllCommentsByNewsId(Pageable pageable, UUID newsId);
    CommentPortResult readCommentByIdAndNewsId(UUID commentId, UUID newsId);
}
