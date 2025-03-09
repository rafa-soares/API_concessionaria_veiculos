package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    VehicleDto save(Vehicle vehicle);

    List<VehicleDto> findByBrandAndPeriod(String brand, Integer start, Integer end);

    List<VehicleDto> findByColorAndYear(String color, Integer year);

    String findByAverageSpeed(String brand);

    List<VehicleDto> findByFuelType(String type);

    List<VehicleDto> findByTransmission(String transmission);

    String findByAverageCapacity(String brand);

    void delete(Long id);
}
