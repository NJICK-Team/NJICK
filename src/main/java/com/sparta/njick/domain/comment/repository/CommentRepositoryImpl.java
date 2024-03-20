package com.sparta.njick.domain.comment.repository;

import com.sparta.njick.domain.comment.entity.Comment;
import com.sparta.njick.domain.comment.exception.CommentErrorCode;
import com.sparta.njick.domain.comment.exception.CustomCommentException;
import com.sparta.njick.domain.comment.model.CommentModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public List<CommentModel> findAllByCardId(Long cardId) {
        return commentJpaRepository.findAllByCardId(cardId).stream().map(Comment::toModel).toList();
    }

    @Override
    public CommentModel update(Long commentId, String content) {
        Comment comment = commentJpaRepository.findById(commentId).orElseThrow(
            () -> new CustomCommentException(CommentErrorCode.COMMENT_ERROR_CODE_NOT_FOUND)
        );
        comment.update(content);
        return comment.toModel();
    }

    @Override
    public void deleteById(Long commentId) {
        commentJpaRepository.deleteById(commentId);
    }

    @Override
    public void findByIdOrElseThrow(Long commentId) {
        commentJpaRepository.findById(commentId).orElseThrow(
            () -> new CustomCommentException(CommentErrorCode.COMMENT_ERROR_CODE_NOT_FOUND)
        );
    }
}
