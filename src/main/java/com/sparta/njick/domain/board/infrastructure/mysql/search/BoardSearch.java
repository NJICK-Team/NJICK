package com.sparta.njick.domain.board.infrastructure.mysql.search;

import com.sparta.njick.domain.board.infrastructure.mysql.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<BoardEntity> searchAllParticipateBoard(Long userId, Pageable pageable);
}
