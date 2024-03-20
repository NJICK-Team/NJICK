package com.sparta.njick.domain.assign.model;

import java.util.ArrayList;
import java.util.List;

public class Assigns {

    private final List<Assign> assigns;

    public Assigns(List<Assign> assigns) {
        this.assigns = new ArrayList<>(assigns);
    }

    public List<Assign> getAssigns() {
        return new ArrayList<>(assigns);
    }
}
