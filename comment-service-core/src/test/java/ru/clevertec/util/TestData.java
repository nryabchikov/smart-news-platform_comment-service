package ru.clevertec.util;

import ru.clevertec.domain.Comment;
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

    public static Comment generateComment() {
        return Comment.builder()
                .id(UUID.randomUUID())
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentCreateCommand generateCommentCreateCommand() {
        return CommentCreateCommand.builder()
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentUpdateCommand generateCommentUpdateCommand() {
        return CommentUpdateCommand.builder()
                .id(UUID.randomUUID())
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentPortResult generateCommentPortResult() {
        return CommentPortResult.builder()
                .id(UUID.randomUUID())
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentUseCaseResult generateCommentUseCaseResult() {
        return CommentUseCaseResult.builder()
                .id(UUID.randomUUID())
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentCreatePortCommand generateCommentCreatePortCommand() {
        return CommentCreatePortCommand.builder()
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static CommentUpdatePortCommand generateCommentUpdatePortCommand() {
        return CommentUpdatePortCommand.builder()
                .id(UUID.randomUUID())
                .text("Some text!")
                .time(LocalDateTime.now())
                .newsId(UUID.randomUUID())
                .authorId(UUID.randomUUID())
                .build();
    }

    public static List<CommentPortResult> generateListOfCommentPortResults() {
        int count = (int) (Math.random() * 100) + 3;
        ArrayList<CommentPortResult> commentPortResults = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            commentPortResults.add(generateCommentPortResult());
        }
        return commentPortResults;
    }







//    public static News generateNews() {
//        return News.builder()
//                .id(UUID.randomUUID())
//                .title("Title")
//                .text("Some text...")
//                .time(LocalDateTime.now())
//                .build();
//    }
//
//    public static List<NewsPortResult> generateListOfNewsPortResults() {
//        int count = (int) (Math.random() * 100);
//        ArrayList<NewsPortResult> newsPortResults = new ArrayList<>(count);
//        for (int i = 0; i < count; i++) {
//            newsPortResults.add(generateNewsPortResult());
//        }
//        return newsPortResults;
//    }
}
