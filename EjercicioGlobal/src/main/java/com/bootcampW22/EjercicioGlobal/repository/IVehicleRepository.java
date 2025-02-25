package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleRepository {
    List<Vehicle> findAll();

    Vehicle saveVehicle(Vehicle vehicle);

    List<Vehicle> findById(Long id);

    List<Vehicle> findByBrandAndPeriod(String brand, Integer start, Integer end);

    void delete(Long id);
}
