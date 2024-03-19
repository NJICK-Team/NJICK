package com.sparta.njick.domain.taskState.entity;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLRestriction(value = "deleted_date is NULL")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TaskStates")
public class TaskState extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskStateId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long boardId;

    public TaskState(TaskStateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.boardId = requestDto.getBoardId();
    }

    public TaskStateModel toModel() {
        return TaskStateModel.builder()
            .taskStateId(taskStateId)
            .name(name)
            .boardId(boardId)
            .build();
    }
}
