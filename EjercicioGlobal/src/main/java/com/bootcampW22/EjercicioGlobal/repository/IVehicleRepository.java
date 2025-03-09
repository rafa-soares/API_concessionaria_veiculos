package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    Vehicle save(Vehicle vehicle);

    List<Vehicle> findById(Long id);

    List<Vehicle> findByBrandAndPeriod(String brand, Integer start, Integer end);

    List<Vehicle> findByColorAndYear(String color, Integer year);

    List<Vehicle> findByBrand(String brand);

    List<Vehicle> findByFuelType(String type);

    List<Vehicle> findByTransmission(String transmission);

    void delete(Long id);
}
