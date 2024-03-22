package com.sparta.njick.domain.assign.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssignJpaRepository extends JpaRepository<AssignEntity, Long> {

    @Query("select a from AssignEntity a where a.cardId = :cardId")
    List<AssignEntity> findAllByCardId(Long cardId);

    @Modifying
    @Query("delete from AssignEntity a where a.cardId = :cardId")
    void deleteAllByCardId(@Param("cardId") Long cardId);
}
