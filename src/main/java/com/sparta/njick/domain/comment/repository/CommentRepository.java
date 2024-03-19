package com.sparta.njick.domain.comment.repository;

import com.sparta.njick.domain.comment.entity.Comment;

public interface CommentRepository {

    void save(Comment comment);
}
