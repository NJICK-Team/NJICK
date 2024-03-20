package com.sparta.njick.domain.assign.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Assign {

    private final Long userId;
    private final Long cardId;
}
