package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.entity.Comment;
import com.sparta.njick.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    @Override
    public void createComment(CommentRequestDto requestDto, Long boardId, Long cardId,
        Long userId) {
        //TODO 보드 참여자x 카드 참여자?
        boardRepository.isParticipated(requestDto.getBoardId(), userId);

        commentRepository.save(new Comment(requestDto.getContent(), userId, cardId));
    }
}
