package ru.clevertec.adapter.input.web.comment.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CommentResponse(
        UUID id,
        String text,
        LocalDateTime time,
        UUID newsId,
        UUID authorId
) {}
