package com.workintech.zoo.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Koala {
    private Integer id;
    private String name;
    private Double sleepHour;
    private Double weight;

    private String gender;
}
