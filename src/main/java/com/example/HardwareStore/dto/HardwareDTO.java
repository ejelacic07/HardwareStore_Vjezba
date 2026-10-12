package com.example.HardwareStore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {

    @NotBlank(message = "Item code cannot be blank.")
    private String code;

    @NotBlank(message = "Item name cannot be blank.")
    private String name;

    @Positive(message = "Item price cannot be blank.")
    private double price;

    @NotBlank(message = "Item type name cannot be blank.")
    private String typeName;

    @DecimalMin(message = "Item amount cannot be blank.", value = "0.0")
    private Integer amount;



/*
    public HardwareDTO(Hardware hardware) {
        this.code = hardware.getCode();
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.type = hardware.getType().toString();
        this.amount = hardware.getAmount();
    }

    */


}
