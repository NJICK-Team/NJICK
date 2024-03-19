package com.sparta.njick.domain.board.model;

import com.sparta.njick.global.exception.CustomRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("보드 모델 객체 유닛 테스트")
class BoardTest {

    @Test
    @DisplayName("[예외] 보드는 생성자가 아닌 사용자가 수정 요청시 예외를 발생한다")
    void when_update_request_by_anonymous_update_expect_exception() {
        //given
        Board board = Board.builder()
                .creatorId(1L)
                .build();

        //when
        assertThatThrownBy(() -> board.update("title", "description", "ffffff", 10L))
                .isInstanceOf(CustomRuntimeException.class)
                .hasMessage("[ERROR] 권한이 없습니다");
        //then
    }

    @Test
    @DisplayName("[성공] 생성자는 생성한 보드를 수정할 수 있다")
    void when_Update_request_by_creator_update_expect_exception() {
        //given
        Board board = Board.builder()
                .creatorId(1L)
                .build();

        //when
        Board updated = board.update("title", "description", "ffffff", 1L);

        //then
        assertThat(updated.getCreatorId()).isEqualTo(1L);
        assertThat(updated.getTitle()).isEqualTo("title");
        assertThat(updated.getDescription()).isEqualTo("description");
    }

    @Test
    @DisplayName("[성공] 보드는 생성자가 아닌지를 판단할 수 있고, 생성자인 경우 false를 반환한다")
    void when_isNotOwner_by_creator_expect_false() {
        //given
        Board board = Board.builder()
                .creatorId(1L)
                .build();

        //when
        boolean result = board.isNotOwner(1L);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("[성공] 보드는 생성자가 아닌지를 판단할 수 있고, 생성자가 아닌 경우 true를 반환한다")
    void when_isNotOwner_by_anonymous_expect_false() {
        //given
        Board board = Board.builder()
                .creatorId(1L)
                .build();

        //when
        boolean result = board.isNotOwner(10L);

        //then
        assertThat(result).isTrue();
    }
}
