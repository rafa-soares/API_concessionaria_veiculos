package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    VehicleDto saveVehicle(Vehicle vehicle);

    List<VehicleDto> findByBrandAndPeriod(String brand, Integer start, Integer end);

    void delete(Long id);
}
