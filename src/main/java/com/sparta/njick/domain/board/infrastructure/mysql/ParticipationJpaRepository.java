package com.sparta.njick.domain.board.infrastructure.mysql;

import com.sparta.njick.domain.board.infrastructure.mysql.entity.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationJpaRepository extends JpaRepository<ParticipationEntity, Long> {
    Optional<ParticipationEntity> findByParticipatorIdAndBoardId(Long participatorId, Long boardId);

    void deleteAllByBoardId(Long boardId);
}
