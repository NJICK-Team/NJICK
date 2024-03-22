package com.sparta.njick.domain.card.infrastructure.entity;

import com.sparta.njick.domain.card.infrastructure.search.CardSearch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardJpaRepository extends JpaRepository<CardEntity, Long>, CardSearch {

    List<CardEntity> findAllByTaskStateId(Long taskStateId);
}
