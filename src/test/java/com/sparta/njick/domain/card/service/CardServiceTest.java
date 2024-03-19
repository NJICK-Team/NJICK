package com.sparta.njick.domain.card.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.sparta.njick.domain.card.dto.response.CardResponseDto;
import com.sparta.njick.domain.card.fixture.CardFixture;
import com.sparta.njick.domain.card.infrastructure.entity.CardEntity;
import com.sparta.njick.domain.card.model.Card;
import com.sparta.njick.domain.card.repository.CardRepository;
import com.sparta.njick.domain.card.repository.CardRepositoryImpl;
import java.util.Optional;
import org.assertj.core.api.Assertions;
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

    @DisplayName("카드 생성")
    @Test
    void createCard() {
        //given
        given(cardRepository.save(any(Card.class))).willReturn(TEST_CARD_INFO_DTO);

        //when & then
        CardResponseDto result = cardService.createCard(TEST_CREATE_REQUEST_DTO, TEST_BOARD_ID,
            TEST_USER_ID);
        assertThat(result.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(result.getAssignedUserIds().size()).isEqualTo(3);
    }
}
