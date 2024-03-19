package com.sparta.njick.domain.board.infrastructure.mysql.entity;

import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Participations")
@Entity
public class ParticipationEntity extends BaseAuditing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long participatorId;

    @Column(nullable = false)
    private Long boardId;
}
