package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.board.repository.BoardRepository;
import com.sparta.njick.domain.card.repository.CardRepository;
import com.sparta.njick.domain.comment.dto.requestDto.CommentRequestDto;
import com.sparta.njick.domain.comment.dto.responseDto.CommentResponseDto;
import com.sparta.njick.domain.comment.entity.Comment;
import com.sparta.njick.domain.comment.model.CommentModel;
import com.sparta.njick.domain.comment.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final CardRepository cardRepository;


    @Override
    public void createComment(CommentRequestDto requestDto, Long boardId, Long cardId,
        Long userId) {
        isParticipated(boardId, userId);
        isExist(cardId);

        commentRepository.save(new Comment(requestDto.getContent(), userId, cardId));
    }

    @Override
    public List<CommentResponseDto> getComments(Long boardId, Long cardId, Long userId) {
        isParticipated(boardId, userId);
        isExist(cardId);

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
        isParticipated(boardId, userId);
        isExist(cardId);
        commentRepository.findByIdOrElseThrow(commentId);

        CommentModel model = commentRepository.update(commentId, requestDto.getContent());

        return new CommentResponseDto(model);
    }

    @Override
    public void deleteComment(Long boardId, Long cardId, Long commentId, Long userId) {
        isParticipated(boardId, userId);
        isExist(cardId);
        commentRepository.findByIdOrElseThrow(commentId);

        commentRepository.deleteById(commentId);
    }

    private void isParticipated(Long boardId, Long userId) {
        if (!boardRepository.isParticipated(boardId, userId)) {
            throw new EntityNotFoundException("보드에 초대된 사용자가 아닙니다.");
        }
    }

    private void isExist(Long cardId) {
        //TODO isExist() 구현 후 주석 해제
       /* if (!cardRepository.isExist(cardId)) {
            throw new EntityNotFoundException("카드가 없습니다.");
        }*/
    }
}
