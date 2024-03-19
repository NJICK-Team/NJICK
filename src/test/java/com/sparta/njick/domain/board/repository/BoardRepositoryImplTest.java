package com.sparta.njick.domain.board.repository;

import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.global.exception.CustomRuntimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@SpringBootTest
@DisplayName("보드 레파지토리 유닛 테스트")
class BoardRepositoryImplTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("[성공] 새로운 보드를 생성할 수 있다")
    void when_register_expect_register_new_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorId = 1L;

        //when
        Board register = boardRepository.register(title, description, color, creatorId);

        //then
        assertThat(register).isNotNull();
        assertThat(register.getTitle()).isEqualTo(title);
        assertThat(register.getDescription()).isEqualTo(description);
        assertThat(register.getCreatorId()).isEqualTo(creatorId);
    }

    @Test
    @DisplayName("[성공] 사용자가 생성한 보드의 title, description, color 항목을 변경할 수 있다.")
    void when_update_expect_update_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorId = 1L;

        Board register = boardRepository.register(title, description, color, creatorId);

        //when
        String newTitle = "new title";
        String newDescription = "new description";
        String newColor = "292929";

        Board updateBoard = register.update(newTitle, newDescription, newColor, creatorId);
        Board result = boardRepository.update(updateBoard);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(newTitle);
        assertThat(result.getDescription()).isEqualTo(newDescription);
        assertThat(result.getColor()).isEqualTo(newColor);
    }

    @Test
    @DisplayName("[성공] 보드의 ID를 통해서 단 건 조회할 수 있다")
    void when_findById_expect_find_specific_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorId = 1L;
        Board register = boardRepository.register(title, description, color, creatorId);

        //when
        Board found = boardRepository.findById(register.getId());

        //then
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo(title);
        assertThat(found.getDescription()).isEqualTo(description);
        assertThat(found.getCreatorId()).isEqualTo(creatorId);
    }

    @Test
    @DisplayName("[성공] 보드를 생성한 사용자는 생성한 전체 보드를 조회할 수 있다")
    void when_searchAllByCreatorId_expect_list_of_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorIdA = 1L;
        Long creatorIdB = 2L;
        Pageable pageable = PageRequest.of(0, 10);
        boardRepository.register(title, description, color, creatorIdA);
        boardRepository.register(title, description, color, creatorIdB);

        //when
        List<Board> result = boardRepository.searchAllByCreatorId(creatorIdA, pageable);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getDescription()).isEqualTo(description);
        assertThat(result.get(0).getCreatorId()).isEqualTo(creatorIdA);
    }

    @Test
    @DisplayName("[성공] 사용자는 참여하고 있는 전체 보드를 조회할 수 있다")
    void when_searchAllParticipateBoard_expect_list_of_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long participatorId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Board registered = boardRepository.register(title, description, color, participatorId);
        boardRepository.participate(registered.getId(), participatorId);

        //when
        List<Board> result = boardRepository.searchAllParticipateBoard(participatorId, pageable);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("[성공] 사용자는 보드에 참여할 수 있다")
    void when_participate_expect_user_participate_in_board() {
        //given
        Long participatorId = 1L;
        Long boardId = 1L;

        //when & then
        assertDoesNotThrow(() -> boardRepository.participate(boardId, participatorId));
    }

    @Test
    @DisplayName("[성공] 사용자는 보드에 참여 여부를 조회할 수 있다")
    void when_isParticipated_expect_user_participate_in_board() {
        //given
        Long participatorId = 1L;
        Long boardId = 1L;
        boardRepository.participate(boardId, participatorId);

        //when
        boolean result = boardRepository.isParticipated(boardId, participatorId);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("[성공] 보드를 생성한 사용자는 보드를 삭제할 수 있다")
    void when_delete_expect_delete_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorId = 1L;
        Board register = boardRepository.register(title, description, color, creatorId);

        //when
        boardRepository.delete(register.getId());

        //then
        assertThatThrownBy(() -> boardRepository.findById(register.getId()))
                .isInstanceOf(CustomRuntimeException.class);
    }
}
