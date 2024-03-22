package com.sparta.njick.domain.card.infrastructure.entity;

import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE cards SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "cards", indexes = @Index(name = "idx_board_id", columnList = "board_id"))
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
    private String cardColor;

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
            .id(id)
            .title(title)
            .description(description)
            .cardColor(cardColor)
            .deadline(deadline)
            .boardId(boardId)
            .taskStateId(taskStateId)
            .creatorId(creatorId)
            .build();
    }

    public static CardEntity fromModel(Card model) {
        return CardEntity.builder()
            .id(model.getId())
            .title(model.getTitle())
            .description(model.getDescription())
            .cardColor(model.getCardColor())
            .deadline(model.getDeadline())
            .boardId(model.getBoardId())
            .taskStateId(model.getTaskStateId())
            .creatorId(model.getCreatorId())
            .build();
    }
}
