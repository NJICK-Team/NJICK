package com.sparta.njick.domain.comment.service;

import com.sparta.njick.domain.card.repository.CardRepository;
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
    private final CardRepository cardRepository;

    @Override
    public void createComment(CommentRequestDto requestDto, Long cardId,
        Long userId) {
        isExist(cardId);

        commentRepository.save(new Comment(requestDto.getContent(), userId, cardId));
    }

    @Override
    public List<CommentResponseDto> getComments(Long cardId, Long userId) {
        isExist(cardId);

        List<CommentResponseDto> response = new ArrayList<>();
        List<CommentModel> models = commentRepository.findAllByCardId(cardId);

        for (CommentModel model : models) {
            response.add(new CommentResponseDto(model));
        }

        return response;
    }

    @Override
    public CommentResponseDto updateComment(CommentRequestDto requestDto, Long cardId,
        Long commentId, Long userId) {
        isExist(cardId);
        commentRepository.findByIdOrElseThrow(commentId);

        //사용자가 맞는지 확인

        CommentModel model = commentRepository.update(commentId, requestDto.getContent(), userId);

        return new CommentResponseDto(model);
    }

    @Override
    public void deleteComment(Long cardId, Long commentId, Long userId) {
        isExist(cardId);
        commentRepository.findByIdOrElseThrow(commentId);

        //사용자가 맞는지 확인

        commentRepository.deleteById(commentId, userId);
    }

    private void isExist(Long cardId) {
        //TODO isExist() 구현 후 주석 해제
       /* if (!cardRepository.isExist(cardId)) {
            throw new EntityNotFoundException("카드가 없습니다.");
        }*/
    }
}
