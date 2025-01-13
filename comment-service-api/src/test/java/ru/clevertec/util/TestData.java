package ru.clevertec.util;

import org.assertj.core.groups.Tuple;
import ru.clevertec.adapter.input.web.comment.dto.CommentRequest;
import ru.clevertec.adapter.input.web.comment.dto.CommentResponse;
import ru.clevertec.adapter.input.web.comment.dto.CommentUpdateRequest;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.port.input.command.CommentCreateCommand;
import ru.clevertec.port.input.command.CommentUpdateCommand;
import ru.clevertec.port.input.command.CommentUseCaseResult;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;
import ru.clevertec.port.output.port.CommentUpdatePortCommand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {

    private static final LocalDateTime FIXED_TIME = LocalDateTime.parse("2024-12-18T15:30:00");

    public static CommentEntity generateCommentEntity() {
        return CommentEntity.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentResponse generateCommentResponse() {
        return CommentResponse.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentResponse generateCommentResponse2() {
        return CommentResponse.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000011"))
                .text("Полезная информация.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                .authorId(UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffffffff"))
                .build();
    }

    public static CommentCreateCommand generateCommentCreateCommand() {
        return CommentCreateCommand.builder()
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentUpdateCommand generateCommentUpdateCommand() {
        return CommentUpdateCommand.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentUpdateRequest generateCommentUpdateRequest() {
        return CommentUpdateRequest.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .build();
    }

    public static CommentRequest generateCommentRequest() {
        return CommentRequest.builder()
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .build();
    }

    public static CommentPortResult generateCommentPortResult() {
        return CommentPortResult.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentUseCaseResult generateCommentUseCaseResult() {
        return CommentUseCaseResult.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentUpdatePortCommand generateCommentUpdatePortCommand() {
        return CommentUpdatePortCommand.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000010"))
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static CommentCreatePortCommand generateCommentCreatePortCommand() {
        return CommentCreatePortCommand.builder()
                .text("Достаточно спорное мнение.")
                .time(FIXED_TIME)
                .newsId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .authorId(UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"))
                .build();
    }

    public static List<Tuple> generateTuplesOfCommentEntities() {
        return List.of(
                Tuple.tuple(
                        "Интересная статья!",
                        UUID.fromString("11111111-1111-1111-1111-111111111111"),
                        UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                ),
                Tuple.tuple(
                        "Полностью согласен.",
                        UUID.fromString("11111111-1111-1111-1111-111111111111"),
                        UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                ),
                Tuple.tuple(
                        "Есть о чем задуматься.",
                        UUID.fromString("11111111-1111-1111-1111-111111111111"),
                        UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
                )
        );
    }

    public static List<CommentEntity> generateListOfCommentEntities() {
        int count = (int) (Math.random() * 100) + 3;
        ArrayList<CommentEntity> commentEntities = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            commentEntities.add(generateCommentEntity());
        }
        return commentEntities;
    }

    public static List<CommentUseCaseResult> generateListOfCommentUseCaseResults() {
        int count = (int) (Math.random() * 100) + 3;
        ArrayList<CommentUseCaseResult> commentUseCaseResults = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            commentUseCaseResults.add(generateCommentUseCaseResult());
        }
        return commentUseCaseResults;
    }
}
