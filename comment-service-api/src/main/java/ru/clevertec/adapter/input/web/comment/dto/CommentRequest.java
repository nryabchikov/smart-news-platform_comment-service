package ru.clevertec.adapter.input.web.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentRequest(
        @NotBlank(message = "Text should not be blank.") String text,

        @NotNull(message = "Comment date and time should be provided.")
        @PastOrPresent(message = "Comment date and time should be in the past or today.")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime time
) {
}