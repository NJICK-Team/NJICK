package com.sparta.njick.domain.taskState.dto.responseDto;

import lombok.Getter;

@Getter
public class TaskStateResponseDto {
	private Long taskStateId;
	private String name;
	private Long boardId;

	public TaskStateResponseDto(Long taskStateId, String name, Long boardId){
		this.taskStateId=taskStateId;
		this.name=name;
		this.boardId=boardId;
	}
}
