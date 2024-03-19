package com.sparta.njick.domain.assign.repository;

import com.sparta.njick.domain.assign.infrastructure.AssignEntity;
import com.sparta.njick.domain.assign.infrastructure.AssignJpaRepository;
import com.sparta.njick.domain.assign.model.Assign;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AssignRepositoryImpl implements AssignRepository {

    private final AssignJpaRepository assignJpaRepository;

    @Override
    public Assign save(Assign model) {
        AssignEntity entity = AssignEntity.builder()
            .userId(model.getUserId())
            .cardId(model.getCardId())
            .build();
        return assignJpaRepository.save(entity).toModel();
    }

    public void saveAll(List<Assign> assigns) {
        List<AssignEntity> entities = assigns.stream()
            .map(it -> AssignEntity.builder()
                .cardId(it.getCardId())
                .userId(it.getUserId())
                .build())
            .toList();
        assignJpaRepository.saveAll(entities);
    }
}
