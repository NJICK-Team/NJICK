package com.sparta.njick.domain.board.service;

import com.sparta.njick.domain.board.controller.dto.request.BoardRegisterDTO;
import com.sparta.njick.domain.board.controller.dto.request.DeleteBoardDTO;
import com.sparta.njick.domain.board.controller.dto.request.UpdateBoardDTO;
import com.sparta.njick.domain.board.mock.FakeBoardRepository;
import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.domain.board.service.dto.BoardInfoDTO;
import com.sparta.njick.domain.board.controller.dto.request.BoardParticipateDTO;
import com.sparta.njick.global.exception.CustomRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("보드 서비스 레이어 유닛 테스트")
class BoardServiceImplTest {
    @Autowired
    private BoardServiceImpl boardService;

    private FakeBoardRepository fakeBoardRepository;

    @BeforeEach
    void init() {
        Board initBoard = Board.builder()
                .id(1L)
                .title("init title")
                .description("init description")
                .color("ffffff")
                .creatorId(1L)
                .build();

        fakeBoardRepository = new FakeBoardRepository(initBoard);
        boardService = new BoardServiceImpl(fakeBoardRepository);
    }


    @Test
    @DisplayName("[성공] 새로운 보드를 생성할 수 있다")
    void when_register_expect_register_new_board() {
        //given
        String title = "test title";
        String description = "test description";
        String color = "ffffff";
        Long creatorId = 1L;
        BoardRegisterDTO boardRegisterRequestDTO = new BoardRegisterDTO(title, description, color, creatorId);

        //when
        BoardInfoDTO register = boardService.register(boardRegisterRequestDTO);

        //then
        assertThat(register).isNotNull();
        assertThat(register.title()).isEqualTo(title);
        assertThat(register.description()).isEqualTo(description);
        assertThat(register.creatorId()).isEqualTo(creatorId);
    }

    @Test
    @DisplayName("[성공] 사용자가 생성한 보드의 title, description, color 항목을 변경할 수 있다")
    void when_update_request_by_creator_expect_update_board() {
        //given
        String newTitle = "test title";
        String newDescription = "test description";
        String newColor = "ffffff";
        UpdateBoardDTO boardDTO = new UpdateBoardDTO(1L, 1L, newTitle, newDescription, newColor);

        //when
        BoardInfoDTO register = boardService.update(boardDTO);

        //then
        assertThat(register).isNotNull();
        assertThat(register.title()).isEqualTo(newTitle);
        assertThat(register.description()).isEqualTo(newDescription);
        assertThat(register.color()).isEqualTo(newColor);
    }

    @Test
    @DisplayName("[예외] 보드의 생성자가 아닌 사용자가 수정 요청시 예외가 발생한다")
    void when_update_request_by_not_creator_expect_throw_exception() {
        //given
        String newTitle = "test title";
        String newDescription = "test description";
        String newColor = "ffffff";
        UpdateBoardDTO boardDTO = new UpdateBoardDTO(1L, 100L, newTitle, newDescription, newColor);

        //when & then
        assertThatThrownBy(() -> boardService.update(boardDTO))
                .isInstanceOf(CustomRuntimeException.class)
                .hasMessage("[ERROR] 권한이 없습니다");
    }

    @Test
    @DisplayName("[성공] 보드의 생성자는 보드를 삭제할 수 있다")
    void when_delete_request_by_creator_expect_delete_board() {
        //given
        DeleteBoardDTO boardDTO = new DeleteBoardDTO(1L, 1L);

        //when & then
        assertDoesNotThrow(() -> boardService.delete(boardDTO));
    }

    @Test
    @DisplayName("[예외] 보드의 생성자가 아닌 사용자가 삭제 요청시 예외가 발생한다")
    void when_delete_request_by_not_creator_expect_throw_exception() {
        //given
        DeleteBoardDTO boardDTO = new DeleteBoardDTO(1L, 100L);

        //when & then
        assertThatThrownBy(() -> boardService.delete(boardDTO))
                .isInstanceOf(CustomRuntimeException.class)
                .hasMessage("[ERROR] 권한이 없습니다");
    }

    @Test
    @DisplayName("[성공] 보드에 참여하고 있는 사용자는 중복 참여할 수 없다")
    void when_participate_request_by_already_participator_expect_throw_exception() {
        //give
        fakeBoardRepository.setIsParticipate(true);
        BoardParticipateDTO dto = new BoardParticipateDTO(1L, 1L);

        //when & then
        assertThatThrownBy(() -> boardService.participate(dto))
                .isInstanceOf(CustomRuntimeException.class)
                // TODO: update exception message
                .hasMessage("[ERROR] 참여중인 보드입니다");
        //then
    }

    @Test
    @DisplayName("[예외] 보드가 없는 경우 예외를 발생시킨다")
    void when_participate_request_invalid_board_expect_throw_exception() {
        //give
        fakeBoardRepository.setIsParticipate(false);
        BoardParticipateDTO dto = new BoardParticipateDTO(100L, 1L);

        //when & then
        assertThatThrownBy(() ->  boardService.participate(dto))
                .isInstanceOf(CustomRuntimeException.class)
                .hasMessage("[ERROR] 잘못된 요청입니다");
    }

    @Test
    @DisplayName("[성공] 사용자는 보드에 참여할 수 있다")
    void when_participate_expect_participate_in_board() {
        //give
        fakeBoardRepository.setIsParticipate(false);
        BoardParticipateDTO dto = new BoardParticipateDTO(1L, 1L);

        //when & then
        assertDoesNotThrow(() -> boardService.participate(dto));
    }

    @Test
    @DisplayName("[성공] 사용자는 생성한 전체 보드를 조회할 수 있다")
    void when_searchAllOwnedBoard_expect_list_of_board() {
        //given & when
        List<BoardInfoDTO> result = boardService.searchAllOwnedBoard(1L);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("[성공] 사용자는 참여하고 있는 전체 보드를 조회할 수 있다")
    void when_searchAllParticipateBoard_expect_list_of_board() {
        //given & when
        List<BoardInfoDTO> result = boardService.searchAllParticipateBoard(1L);

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

    }
}
