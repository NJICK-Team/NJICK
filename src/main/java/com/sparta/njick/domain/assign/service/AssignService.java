package com.sparta.njick.domain.assign.service;

import com.sparta.njick.domain.assign.model.Assign;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AssignService {

    void saveAll(List<Assign> assigns);
}
