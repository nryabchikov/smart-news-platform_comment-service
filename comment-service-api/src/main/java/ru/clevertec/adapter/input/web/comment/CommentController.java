package ru.clevertec.adapter.input.web.comment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.adapter.input.web.comment.dto.CommentRequest;
import ru.clevertec.adapter.input.web.comment.dto.CommentResponse;
import ru.clevertec.adapter.input.web.comment.dto.CommentUpdateRequest;
import ru.clevertec.port.input.CreateCommentUseCase;
import ru.clevertec.port.input.DeleteCommentUseCase;
import ru.clevertec.port.input.ReadCommentUseCase;
import ru.clevertec.port.input.UpdateCommentUseCase;
import ru.clevertec.port.input.command.CommentCreateCommand;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news/{newsId}/comments")
public class CommentController {
    private final CreateCommentUseCase createCommentUseCase;
    private final ReadCommentUseCase readCommentUseCase;
    private final UpdateCommentUseCase updateCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;

    private final WebCommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> readAllComments(
            @PathVariable("newsId") UUID newsId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "3") int pageSize) {

        List<CommentResponse> commentResponses =
                readCommentUseCase.readAllCommentsByNewsId(newsId, pageNumber, pageSize).stream()
                        .map(commentMapper::toCommentResponse)
                        .toList();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentResponses);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> readCommentById(@PathVariable("newsId") @Valid @NotNull UUID newsId,
                                                           @PathVariable("commentId") @Valid @NotNull UUID commentId) {

        CommentResponse commentResponse = commentMapper.toCommentResponse(
                readCommentUseCase.readCommentByIdAndNewsId(commentId, newsId)
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentResponse);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@PathVariable("newsId") @Valid @NotNull UUID newsId,
                                                      @RequestBody @Valid CommentRequest commentRequest,
                                                      Principal principal) {

        CommentCreateCommand commentCreateCommand = commentMapper.toCommentCreateCommand(commentRequest);
        CommentResponse commentResponse = commentMapper.toCommentResponse(
                createCommentUseCase.createComment(newsId, commentCreateCommand, UUID.fromString(principal.getName()))
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentResponse);
    }

    @PutMapping
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("newsId") @Valid @NotNull UUID newsId,
                                                         @RequestBody @Valid CommentUpdateRequest commentUpdateRequest) {

        CommentResponse commentResponse = commentMapper.toCommentResponse(
                updateCommentUseCase.updateComment(newsId, commentMapper.toCommentUpdateCommand(commentUpdateRequest))
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable("newsId") @Valid @NotNull UUID newsId,
                           @PathVariable("commentId") @Valid @NotNull UUID commentId) {
        deleteCommentUseCase.deleteComment(newsId, commentId);

        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllComments(@PathVariable("newsId") @Valid @NotNull UUID newsId) {

        deleteCommentUseCase.deleteAllComments(newsId);

        return ResponseEntity.noContent()
                .build();
    }
}
