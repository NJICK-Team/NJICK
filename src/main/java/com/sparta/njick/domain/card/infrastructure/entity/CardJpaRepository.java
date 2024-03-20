package com.sparta.njick.domain.card.infrastructure.entity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findAllByTaskStateId(Long taskStateId);
}
