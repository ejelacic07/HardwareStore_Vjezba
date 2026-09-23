package com.example.HardwareStore.dto;

import com.example.HardwareStore.domain.Hardware;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class HardwareDTO {

    @NotBlank(message = "Item code cannot be blank.")
    private String code;

    @NotBlank(message = "Item name cannot be blank.")
    private String name;

    @Positive(message = "Item price cannot be blank.")
    private double price;

    @NotBlank(message = "Item type cannot be blank.")
    private String type;

    @NotNull(message = "Item amount cannot be blank.")
    private Integer amount;


    public HardwareDTO(Hardware hardware) {
        this.code = hardware.getCode();
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.type = hardware.getType().toString();
        this.amount = hardware.getAmount();
    }

}
