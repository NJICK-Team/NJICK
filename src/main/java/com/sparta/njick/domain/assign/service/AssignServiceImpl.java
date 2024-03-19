package com.sparta.njick.domain.assign.service;

import com.sparta.njick.domain.assign.model.Assign;
import com.sparta.njick.domain.assign.repository.AssignRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignServiceImpl implements AssignService {

    private final AssignRepository assignRepository;

    @Override
    public void saveAll(List<Assign> assigns) {
        assignRepository.saveAll(assigns);
    }
}
