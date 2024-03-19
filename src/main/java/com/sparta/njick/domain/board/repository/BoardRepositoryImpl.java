package com.sparta.njick.domain.board.repository;

import com.sparta.njick.domain.board.infrastructure.mysql.BoardJpaRepository;
import com.sparta.njick.domain.board.infrastructure.mysql.ParticipationJpaRepository;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.BoardEntity;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.ParticipationEntity;
import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.global.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.njick.global.exception.errorcode.BoardErrorCode.BOARD_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {
    private final BoardJpaRepository boardJpaRepository;
    private final ParticipationJpaRepository participationJpaRepository;

    @Override
    public Board register(
            String title,
            String description,
            String color,
            Long creatorId
    ) {
        return boardJpaRepository.save(BoardEntity.builder()
                        .title(title)
                        .description(description)
                        .color(color)
                        .creatorId(creatorId)
                        .build())
                .toModel();
    }

    @Override
    public void participate(Long boardId, Long participatorId) {
        participationJpaRepository.save(ParticipationEntity.builder()
                .boardId(boardId)
                .participatorId(participatorId)
                .build());
    }

    @Override
    public boolean isParticipated(Long boardId, Long participatorId) {
        return participationJpaRepository
                .findByParticipatorIdAndBoardId(participatorId, boardId)
                .isPresent();
    }

    @Override
    public Board findById(Long boardId) {
        return boardJpaRepository.findById(boardId)
                .orElseThrow(() ->
                        new CustomRuntimeException(BOARD_NOT_FOUND.getMessage()))
                .toModel();

    }

    @Override
    public Board update(Board updateBoard) {
        return boardJpaRepository
                .saveAndFlush(BoardEntity.fromModel(updateBoard))
                .toModel();
    }

    @Override
    public List<Board> searchAllByCreatorId(Long creatorId, Pageable pageable) {
        return boardJpaRepository.findAllByCreatorId(creatorId, pageable)
                .getContent().stream()
                .map(BoardEntity::toModel)
                .toList();
    }

    @Override
    public List<Board> searchAllParticipateBoard(Long userId, Pageable pageable) {
        return boardJpaRepository.searchAllParticipateBoard(userId, pageable)
                .getContent().stream()
                .map(BoardEntity::toModel)
                .toList();
    }

    @Override
    public void delete(Long boardId) {
        boardJpaRepository.deleteById(boardId);
        participationJpaRepository.deleteAllByBoardId(boardId);
    }
}
