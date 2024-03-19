package com.sparta.njick.domain.board.infrastructure.mysql;

import com.sparta.njick.domain.board.infrastructure.mysql.entity.BoardEntity;
import com.sparta.njick.domain.board.infrastructure.mysql.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long>, BoardSearch {
    Page<BoardEntity> findAllByCreatorId(Long creatorId, Pageable pageable);
}
