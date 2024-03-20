package com.sparta.njick.domain.card.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.assign.model.Assigns;
import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.fixture.CardFixture;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepositoryImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceTest implements CardFixture {

    @InjectMocks
    CardServiceImpl cardService;

    @Mock
    CardRepositoryImpl cardRepository;

    @DisplayName("카드 생성 성공")
    @Test
    void createCard() {
        //given
        given(cardRepository.save(any(CardCreateRequestDto.class),
            eq(TEST_BOARD_ID), eq(TEST_USER_ID))) .willReturn(TEST_CARD);
        given(cardRepository.assignAll(any(), any()))
            .willReturn(new Assigns(List.of(new Assign(1L,1L, 1L),
                new Assign(2L , 2L, 1L))));

        //when
        CardResponseDto result = cardService.createCard(TEST_CREATE_REQUEST_DTO, TEST_BOARD_ID,
            TEST_USER_ID);

        //then
        assertThat(result.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(result.getAssignedUserIds().size()).isEqualTo(2);
    }

    @DisplayName("카드 조회 성공")
    @Test
    void getCard() {
        //given
        given(cardRepository.get(eq(TEST_CARD_ID))).willReturn(TEST_CARD);
        given(cardRepository.findAssignsByCardId(eq(TEST_CARD_ID)))
            .willReturn(new Assigns(List.of(new Assign(1L,1L, 1L),
                new Assign(2L , 2L, 1L))));

        //when
        CardResponseDto result = cardService.getCard(TEST_USER_ID, TEST_BOARD_ID, TEST_CARD_ID);

        //then
        assertThat(result.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(result.getAssignedUserIds().size()).isEqualTo(2);
    }

    @DisplayName("카드 수정 성공")
    @Test
    void updateCard() {
        //given
        given(cardRepository.get(eq(TEST_CARD_ID))).willReturn(TEST_CARD);
        given(cardRepository.update(any(Card.class))).willReturn(ANOTHER_TEST_CARD);
        given(cardRepository.reassignAll(any(), any()))
            .willReturn(new Assigns(List.of(new Assign(1L,4L, 1L),
                new Assign(2L , 5L, 1L))));
        //when
        CardResponseDto result = cardService.updateCard(TEST_UPDATE_REQUEST_DTO, TEST_BOARD_ID,
            TEST_CARD_ID, TEST_USER_ID);

        //then
        assertThat(result.getTitle()).isEqualTo(ANOTHER_PREFIX + TEST_TITLE);
        assertThat(result.getAssignedUserIds().size()).isEqualTo(2);
    }
}
