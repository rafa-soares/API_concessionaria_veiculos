package com.bootcampW22.EjercicioGlobal.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    private Long id;

    @NotNull(value = "O campo 'brand' não pode ser nulo.")
    @NotEmpty(message = "O campo 'brand' não pode ser vazio.")
    private String brand;

    @NotNull(value = "O campo 'model' não pode ser nulo.")
    @NotEmpty(message = "O campo 'model' não pode ser vazio.")
    private String model;

    private String registration;
    private String color;
    private Integer year;
    private String max_speed;
    private Integer passengers;
    private String fuel_type;
    private String transmission;
    private Double length;
    private Double width;
    private Double weight;
}
