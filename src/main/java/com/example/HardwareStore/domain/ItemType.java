package com.example.HardwareStore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {

    CPU(1, "CPU"),
    GPU(2, "GPU"),
    MBO(3, "MBO"),
    RAM(4, "RAM"),
    STORAGE(5, "STORAGE"),
    OTHER(6, "OTHER");

    private final Integer id;
    private final String name;







}
