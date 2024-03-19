package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.dto.responseDto.CommentResponseDto;
import com.sparta.njick.domain.comment.entity.Comment;
import com.sparta.njick.domain.comment.model.CommentModel;
import com.sparta.njick.domain.comment.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
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
        boardRepository.isParticipated(requestDto.getBoardId(), userId);
        //TODO 카드 참여자?

        commentRepository.save(new Comment(requestDto.getContent(), userId, cardId));
    }

    @Override
    public List<CommentResponseDto> getComments(Long boardId, Long cardId, Long userId) {
        boardRepository.isParticipated(boardId, userId);
        //TODO 카드 참여자?

        List<CommentResponseDto> response = new ArrayList<>();
        List<CommentModel> models = commentRepository.findAllByCardId(cardId);

        for (CommentModel model : models) {
            response.add(new CommentResponseDto(model));
        }

        return response;
    }

    @Override
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Long boardId, Long cardId,
        Long commentId, Long userId) {
        boardRepository.isParticipated(boardId, userId);
        //TODO 카드 참여자?

        CommentModel model = commentRepository.update(commentId, requestDto.getContent());

        return new CommentResponseDto(model);
    }
}
