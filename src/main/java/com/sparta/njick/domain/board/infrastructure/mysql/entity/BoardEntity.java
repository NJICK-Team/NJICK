package com.sparta.njick.domain.board.infrastructure.mysql.entity;

import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.*;
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
@Table(name = "boards")
@Entity
@SQLDelete(sql = "UPDATE boards SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
public class BoardEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 6)
    private String color;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long creatorId;

    public Board toModel() {
        return Board.builder()
                .id(id)
                .color(color)
                .title(title)
                .description(description)
                .creatorId(creatorId)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }

    public static BoardEntity fromModel(Board board) {
        return BoardEntity.builder()
                .id(board.getId())
                .color(board.getColor())
                .title(board.getTitle())
                .description(board.getDescription())
                .creatorId(board.getCreatorId())
                .build();
    }
}
