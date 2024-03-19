package com.sparta.njick.domain.assign.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.sparta.njick.domain.assign.model.Assign;
import java.util.List;

public interface AssignRepository {

    Assign save(Assign model);
    void saveAll(List<Assign> assigns);
}
