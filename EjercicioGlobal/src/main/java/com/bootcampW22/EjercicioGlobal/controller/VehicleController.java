package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleDto> saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        return ResponseEntity.status(201).body(vehicleService.saveVehicle(vehicle));
    }

    @GetMapping("/vehicles/search")
    public ResponseEntity<?> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/search/brand/{brand}/between/{start}/{end}")
    public List<VehicleDto> getVehiclesByBrandAndPeriod(@PathVariable(name = "brand") String brand,
                                                        @PathVariable(name = "start") Integer start,
                                                        @PathVariable(name = "end") Integer end) {
        return vehicleService.findByBrandAndPeriod(brand, start, end);
    }

    @DeleteMapping("/vehicle/{id}")
    public void deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.delete(id);
    }
}
