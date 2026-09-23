package com.example.HardwareStore.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {

    private Integer id;
    private String code;
    private String name;
    private  double price;
    private Type type;
    private Integer amount;


}
