package com.sparta.njick.domain.comment.repository;

import com.sparta.njick.domain.comment.entity.Comment;
import com.sparta.njick.domain.comment.model.CommentModel;
import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    List<CommentModel> findAllByCardId(Long cardId);

    CommentModel update(Long commentId, String content);

    void deleteById(Long commentId);

    void findByIdOrElseThrow(Long commentId);
}