package com.sparta.njick.domain.taskState.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStateRequestDto {

    @NotBlank(message = "이름이 없습니다")
    private String name;

    @NotBlank(message = "보드 아이디가 없습니다")
    private Long boardId;
}
