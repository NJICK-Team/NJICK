package com.sparta.njick.domain.card.dto.request;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardPageRequestDto {

    private int page;
    private int size;
    private String sort;

    public Pageable toPageable() {
        if (Objects.isNull(sort)) {
            return PageRequest.of(page - 1, size);
        } else {
            String[] sortArr = sort.split(",");
            Sort.Direction direction = sortArr[1].equalsIgnoreCase("asc") ?
                Direction.ASC : Direction.DESC;
            return PageRequest.of(page - 1, size, Sort.by(direction, sortArr[0]));
        }
    }
}
