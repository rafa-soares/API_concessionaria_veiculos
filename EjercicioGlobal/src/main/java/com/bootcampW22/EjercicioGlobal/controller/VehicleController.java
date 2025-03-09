package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDto> saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        return ResponseEntity.status(201).body(vehicleService.save(vehicle));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/search/color/{color}/year/{year}")
    public ResponseEntity<List<VehicleDto>> getColorAndYear(@PathVariable(name = "color") String color,
                                                            @PathVariable(name = "year") Integer year) {
        return new ResponseEntity<>(vehicleService.findByColorAndYear(color, year), HttpStatus.OK);
    }

    @GetMapping("/search/brand/{brand}/between/{start}/{end}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByBrandAndPeriod(@PathVariable(name = "brand") String brand,
                                                                        @PathVariable(name = "start") Integer start,
                                                                        @PathVariable(name = "end") Integer end) {
        return new ResponseEntity<>(vehicleService.findByBrandAndPeriod(brand, start, end), HttpStatus.OK);
    }

    @GetMapping("/search/average-speed/brand/{brand}")
    public ResponseEntity<String> getAverageSpeed(@PathVariable(name = "brand") String brand) {
        return new ResponseEntity<>(vehicleService.findByAverageSpeed(brand), HttpStatus.OK);
    }

    @GetMapping("/search/fuel/type/{type}")
    public ResponseEntity<List<VehicleDto>> getFuelType(@PathVariable(name = "type") String type) {
        return new ResponseEntity<>(vehicleService.findByFuelType(type), HttpStatus.OK);
    }

    @GetMapping("/search/transmission/{type}")
    public ResponseEntity<List<VehicleDto>> getTransmission(@PathVariable(name = "type") String transmission) {
        return new ResponseEntity<>(vehicleService.findByTransmission(transmission), HttpStatus.OK);
    }

    @GetMapping("/search/average-capacity/brand/{brand}")
    public ResponseEntity<String> findAverageCapacity(@PathVariable(name = "brand") String brand) {
        return new ResponseEntity<>(vehicleService.findByAverageCapacity(brand), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.delete(id);
    }
}
