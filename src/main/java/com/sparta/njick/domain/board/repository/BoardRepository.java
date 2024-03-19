package com.sparta.njick.domain.board.repository;

import com.sparta.njick.domain.board.model.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepository {
    Board register(String title, String description, String color, Long creatorId);

    Board findById(Long boardId);

    List<Board> searchAllByCreatorId(Long creatorId, Pageable pageable);

    List<Board> searchAllParticipateBoard(Long userId, Pageable pageable);

    void participate(Long boardId, Long participatorId);

    boolean isParticipated(Long boardId, Long participatorId);

    Board update(Board updateBoard);

    void delete(Long boardId);
}
