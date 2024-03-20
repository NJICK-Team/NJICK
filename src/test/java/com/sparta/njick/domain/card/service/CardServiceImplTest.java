package com.sparta.njick.domain.card.service;

import static org.assertj.core.api.Assertions.*;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.board.mock.FakeBoardRepository;
import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.request.CardUpdateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.fixture.CardFixture;
import com.sparta.njick.domain.card.mock.FakeCardRepository;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.global.exception.CustomRuntimeException;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardServiceImplTest implements CardFixture {

    private CardServiceImpl cardService;

    private FakeCardRepository fakeCardRepository;
    private FakeBoardRepository fakeBoardRepository;

    @BeforeEach
    void init() {
        Card initCard = Card.builder()
            .id(1L)
            .title("title")
            .description("description")
            .cardColor("0x0000")
            .deadline(LocalDateTime.now())
            .boardId(1L)
            .taskStateId(1L)
            .creatorId(1L)
            .build();

        Assign initAssign = Assign.builder()
            .id(1L)
            .cardId(1L)
            .userId(2L)
            .build();

        Board initBoard = Board.builder()
            .id(1L)
            .title("init title")
            .description("init description")
            .color("ffffff")
            .creatorId(1L)
            .build();

        fakeBoardRepository = new FakeBoardRepository(initBoard);
        fakeCardRepository = new FakeCardRepository(initCard, initAssign);
        cardService = new CardServiceImpl(fakeCardRepository, fakeBoardRepository);
    }

    @DisplayName("[성공] 새로운 카드를 만들 수 있다")
    @Test
    void create_card_success() {
        //given
        CardCreateRequestDto dto = TEST_CREATE_REQUEST_DTO;
        fakeBoardRepository.setIsParticipate(true);

        //when
        CardResponseDto responseDto = cardService.createCard(dto, TEST_BOARD_ID, TEST_USER_ID);

        //then
        assertThat(responseDto.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(responseDto.getAssignedUserIds().size()).isEqualTo(1);
    }

    @DisplayName("[예외] 보드에 등록되지 않은 사용자는 카드를 만들 수 없다")
    @Test
    void create_card_fail() {
        //given
        CardCreateRequestDto dto = TEST_CREATE_REQUEST_DTO;

        //when & then
        assertThatThrownBy(() -> cardService.createCard(dto, TEST_BOARD_ID, TEST_USER_ID))
            .isInstanceOf(CustomRuntimeException.class);
    }

    @DisplayName("[성공] 카드를 수정할 수 있다")
    @Test
    void update_card_success() {
        //given
        CardUpdateRequestDto dto = TEST_UPDATE_REQUEST_DTO;
        fakeBoardRepository.setIsParticipate(true);

        //when
        CardResponseDto responseDto = cardService.updateCard(dto, TEST_BOARD_ID, TEST_CARD_ID,
            TEST_USER_ID);

        //then
        assertThat(responseDto.getTitle()).isEqualTo(ANOTHER_PREFIX + TEST_TITLE);
    }

    @DisplayName("[예외] 보드에 참여하고 있지 않은 유저는 카드를 수정할 수 없다")
    @Test
    void update_card_fail() {
        //given
        CardUpdateRequestDto dto = TEST_UPDATE_REQUEST_DTO;

        //when & then
        assertThatThrownBy(() -> cardService.updateCard(dto, TEST_BOARD_ID, TEST_CARD_ID,
            TEST_USER_ID))
            .isInstanceOf(CustomRuntimeException.class);
    }
}
