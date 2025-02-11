package ru.clevertec.adapter.output.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    Page<CommentEntity> findCommentEntitiesByNewsId(Pageable pageable, UUID newsId);
    Optional<CommentEntity> findCommentEntityByIdAndNewsId(UUID id, UUID newsId);
    void deleteByIdAndNewsId(UUID id, UUID newsId);
    List<CommentEntity> findCommentEntitiesByNewsId(UUID newsId);
}
