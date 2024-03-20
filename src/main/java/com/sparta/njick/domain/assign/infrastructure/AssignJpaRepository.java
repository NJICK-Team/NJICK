package com.sparta.njick.domain.assign.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignJpaRepository extends JpaRepository<AssignEntity, Long> {

    List<AssignEntity> findAllByCardId(Long cardId);

    void deleteAllByCardId(Long cardId);
}
