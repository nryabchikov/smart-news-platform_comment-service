package ru.clevertec.adapter.output.persistence.jpa;

import org.mapstruct.Mapper;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.port.output.port.CommentCreatePortCommand;
import ru.clevertec.port.output.port.CommentPortResult;

@Mapper(componentModel = "spring")
public interface PersistenceCommentMapper {
    CommentPortResult toCommentPortResult(CommentEntity commentEntity);
    CommentEntity toCommentEntity(CommentCreatePortCommand commentCreatePortCommand);
}
