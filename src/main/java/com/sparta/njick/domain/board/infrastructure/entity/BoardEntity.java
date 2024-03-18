package com.sparta.njick.domain.board.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boards")
@Entity
public class BoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: implement Board entity class
}
