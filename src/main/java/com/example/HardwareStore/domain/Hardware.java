package com.example.HardwareStore.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hardware {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private  double price;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private ItemType type;

    private Integer amount;


}
