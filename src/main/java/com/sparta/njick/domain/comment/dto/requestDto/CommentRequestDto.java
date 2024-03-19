package com.sparta.njick.domain.comment.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    @NotBlank(message = "내용이 없습니다")
    private String content;

//    @NotBlank(message = "작성자 아이디가 없습니다")
//    private Long writerId;
//
//    @NotBlank(message = "카드 아이디가 없습니다")
//    private Long cardId;
}
