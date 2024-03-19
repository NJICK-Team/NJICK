package com.sparta.njick.domain.taskState.dto.responseDto;

import com.sparta.njick.domain.taskState.model.TaskStateModel;
import lombok.Getter;

@Getter
public class TaskStateResponseDto {

    private Long taskStateId;
    private String name;
    private Long boardId;

    public TaskStateResponseDto(Long taskStateId, String name, Long boardId) {
        this.taskStateId = taskStateId;
        this.name = name;
        this.boardId = boardId;
    }

    public TaskStateResponseDto(TaskStateModel model) {
        this.taskStateId = model.getTaskStateId();
        this.name = model.getName();
        this.boardId = model.getBoardId();
    }
}
