package com.sparta.njick.domain.taskState.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStateModel {

    private Long taskStateId;
    private String name;
    private Long boardId;
}
