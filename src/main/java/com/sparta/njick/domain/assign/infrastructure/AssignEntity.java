package com.sparta.njick.domain.assign.infrastructure;

import com.sparta.njick.domain.assign.model.Assign;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assigns", indexes = @Index(name = "idx_card_id", columnList = "card_id"))
@Entity
public class AssignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long cardId;

    public Assign toModel() {
        return Assign.builder()
            .id(id)
            .userId(userId)
            .cardId(cardId)
            .build();
    }
}
