package com.sparta.njick.domain.board.mock;

import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.domain.board.repository.BoardRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class FakeBoardRepository implements BoardRepository {
    private final Board board;
    private boolean isParticipate;

    public FakeBoardRepository(Board board, boolean isParticipate) {
        this.board = board;
        this.isParticipate = isParticipate;
    }

    public FakeBoardRepository(Board board) {
        this(board, false);
    }

    @Override
    public Board register(String title, String description, String color, Long creatorId) {
        return Board.builder()
                .title(title)
                .description(description)
                .color(color)
                .creatorId(creatorId)
                .build();
    }

    @Override
    public Board findById(Long boardId) {
        if (board == null || !board.getId().equals(boardId)){
            return null;
        }

        return board;
    }

    @Override
    public List<Board> searchAllByCreatorId(Long creatorId, Pageable pageable) {
        return List.of(board);
    }

    @Override
    public List<Board> searchAllParticipateBoard(Long userId, Pageable pageable) {
        return List.of(board);
    }

    @Override
    public void participate(Long boardId, Long participatorId) {
    }

    @Override
    public boolean isParticipated(Long boardId, Long participatorId) {
        return isParticipate;
    }

    @Override
    public Board update(Board updateBoard) {
        return board.update(
                updateBoard.getTitle(),
                updateBoard.getDescription(),
                updateBoard.getColor(),
                updateBoard.getCreatorId()
        );
    }

    @Override
    public void delete(Long boardId) {
    }

    public void setIsParticipate(boolean isParticipate) {
        this.isParticipate = isParticipate;
    }
}
