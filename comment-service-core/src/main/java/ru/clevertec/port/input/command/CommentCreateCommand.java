package ru.clevertec.port.input.command;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CommentCreateCommand(
        String text,
        LocalDateTime time,
        UUID newsId,
        UUID authorId
) {}
