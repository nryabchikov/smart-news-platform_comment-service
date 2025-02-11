package ru.clevertec.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private UUID id;
    private String text;
    private LocalDateTime time;
    private UUID newsId;
    private UUID authorId;
}
