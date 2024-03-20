package com.sparta.njick.domain.card.dto;

import static org.assertj.core.api.Assertions.*;

import com.sparta.njick.domain.card.dto.request.CardCreateRequestDto;
import com.sparta.njick.domain.card.fixture.CardFixture;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardCreateRequestDtoTest implements CardFixture {

    @DisplayName("생성 성공")
    @Test
    void createSuccess() {
        //given && when
        var result = validate(TEST_CREATE_REQUEST_DTO);

        //then
        assertThat(result).isEmpty();
    }

    @DisplayName("생성 실패")
    @Test
    void createFail() {
        //given
        var requestDto = CardCreateRequestDto.builder()
            .title("")
            .description("")
            .cardColor("")
            .build();

        //when
        var result = validate(requestDto);

        //then
        assertThat(result).hasSize(3);
    }

    private Set<ConstraintViolation<CardCreateRequestDto>> validate(
        CardCreateRequestDto requestDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(requestDto);
    }
}
