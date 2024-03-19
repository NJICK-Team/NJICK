package com.sparta.njick.domain.comment.repository;

import com.sparta.njick.domain.comment.entity.Comment;
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
}
