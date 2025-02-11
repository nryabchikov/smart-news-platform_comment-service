package ru.clevertec.adapter.output.persistence.jpa.repository;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.clevertec.adapter.output.persistence.jpa.entity.CommentEntity;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:db/data.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void shouldFindCommentEntitiesByNewsId() {
        //given
        int pageNumber = 0;
        int pageSize = 3;
        UUID newsId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        List<Tuple> tuplesOfCommentEntities = TestData.generateTuplesOfCommentEntities();

        //when
        Page<CommentEntity> actualCommentEntities =
                commentRepository.findCommentEntitiesByNewsId(PageRequest.of(pageNumber, pageSize), newsId);

        //then
        assertEquals(3, actualCommentEntities.getSize());
        assertThat(actualCommentEntities)
                .extracting("text", "newsId", "authorId")
                .containsExactlyInAnyOrderElementsOf(tuplesOfCommentEntities);
    }

    @Test
    void shouldFindCommentEntityByIdAndNewsId_whenCommentExist() {
        //given
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID commentId = UUID.fromString("00000000-0000-0000-0000-000000000010");
        CommentEntity commentEntity = TestData.generateCommentEntity();

        //when
        Optional<CommentEntity> actualCommentEntity =
                commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId);

        //then
        assertTrue(actualCommentEntity.isPresent());
        assertThat(actualCommentEntity.get())
                .usingRecursiveComparison()
                .ignoringFields("time")
                .isEqualTo(commentEntity);
    }

    @Test
    void shouldNotFindCommentEntityByIdAndNewsId_whenCommentNotExist() {
        //given
        UUID wrongNewsId = UUID.fromString("55555555-5555-5555-5555-555555555556");
        UUID wrongCommentId = UUID.randomUUID();

        //when
        Optional<CommentEntity> actualCommentEntity =
                commentRepository.findCommentEntityByIdAndNewsId(wrongCommentId, wrongNewsId);

        //then
        assertTrue(actualCommentEntity.isEmpty());
    }

    @Test
    void shouldDeleteByIdAndNewsId_whenCommentExist() {
        //given
        int expectedSize = 19;
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID commentId = UUID.fromString("00000000-0000-0000-0000-000000000010");

        //when
        commentRepository.deleteByIdAndNewsId(commentId, newsId);

        //then
        assertEquals(expectedSize, commentRepository.findAll().size());
        Optional<CommentEntity> commentAfterDelete = commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId);
        assertFalse(commentAfterDelete.isPresent());
    }

    @Test
    void shouldNotDeleteByIdAndNewsId_whenCommentNotExist() {
        //given
        int expectedSize = 20;
        UUID newsId = UUID.fromString("33333333-3333-3333-3333-333333333333");
        UUID wrongCommentId = UUID.fromString("00000000-0000-0000-0000-000000000021");

        //when
        commentRepository.deleteByIdAndNewsId(wrongCommentId, newsId);

        //then
        assertEquals(expectedSize, commentRepository.findAll().size());
    }

    @Test
    void shouldNotDeleteByIdAndNewsId_whenNewsNotExist() {
        //given
        int expectedSize = 20;
        UUID wrongNewsId = UUID.fromString("33333333-3333-3333-3333-333333333334");
        UUID newsId = UUID.fromString("55555555-5555-5555-5555-555555555555");
        UUID commentId = UUID.fromString("00000000-0000-0000-0000-000000000020");

        //when
        commentRepository.deleteByIdAndNewsId(commentId, wrongNewsId);

        //then
        assertEquals(expectedSize, commentRepository.findAll().size());
        Optional<CommentEntity> commentEntity = commentRepository.findCommentEntityByIdAndNewsId(commentId, newsId);
        assertTrue(commentEntity.isPresent());
    }
}