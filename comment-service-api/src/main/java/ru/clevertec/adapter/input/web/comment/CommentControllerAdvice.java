package ru.clevertec.adapter.input.web.comment;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.exception.CommentNotFoundException;

@Slf4j
@RestControllerAdvice
public class CommentControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionObject> response400(@RequestBody Exception exception) {
        log.error("CommentControllerAdvice.response400: Unexpected exception caught.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(aggregate(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = {
            ValidationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ExceptionObject> response422(@RequestBody Exception exception) {
        log.error("CommentControllerAdvice.response422: Validation exception caught.", exception);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(aggregate(exception.getLocalizedMessage(), HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(value = {
            CommentNotFoundException.class
    })
    public ResponseEntity<ExceptionObject> response404(@RequestBody Exception exception) {
        log.error("NewsControllerAdvice.response404: NewsNotFoundException caught.", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(aggregate(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ExceptionObject aggregate(String message, HttpStatus httpStatus) {
        return new ExceptionObject(httpStatus.value(), httpStatus.name(), message);
    }

    public record ExceptionObject(int code, String status, String message) {}
}
