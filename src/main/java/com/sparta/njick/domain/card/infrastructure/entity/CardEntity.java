package com.sparta.njick.domain.card.infrastructure.entity;

import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.model.CardColor;
import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
@Entity
public class CardEntity extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private CardColor cardColor;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private Long taskStateId;

    @Column(nullable = false)
    private Long creatorId;

    public Card toModel() {
        return Card.builder()
            .title(title)
            .description(description)
            .cardColor(cardColor)
            .deadline(deadline)
            .boardId(boardId)
            .taskStateId(taskStateId)
            .creatorId(creatorId)
            .build();
    }
}
