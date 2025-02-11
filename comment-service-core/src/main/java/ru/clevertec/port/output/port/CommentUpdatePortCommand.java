package ru.clevertec.port.output.port;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CommentUpdatePortCommand(
        UUID id,
        String text,
        LocalDateTime time,
        UUID newsId,
        UUID authorId
) {}
