package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.bootcampW22.EjercicioGlobal.exception.AlreadyExistsException;
import com.bootcampW22.EjercicioGlobal.exception.InvalidVehicleDataException;
import com.bootcampW22.EjercicioGlobal.exception.NotFoundException;
import com.bootcampW22.EjercicioGlobal.repository.IVehicleRepository;
import com.bootcampW22.EjercicioGlobal.repository.VehicleRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {

    IVehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepositoryImpl vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDto save(Vehicle vehicle) {
//        validateVehicle(vehicle);

        Vehicle response = vehicleRepository.save(vehicle);

        return convertVehicleToDto(response);
    }

    @Override
    public List<VehicleDto> findByBrandAndPeriod(String brand, Integer start, Integer end) {
        List<Vehicle> vehicleList = vehicleRepository.findByBrandAndPeriod(brand, start, end);

        if (vehicleList.contains(null) || vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }
        return vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> findByColorAndYear(String color, Integer year) {
        List<Vehicle> vehicleList = vehicleRepository.findByColorAndYear(color, year);

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        return vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .collect(Collectors.toList());
    }

    @Override
    public String findByAverageSpeed(String brand) {
        List<Vehicle> vehicleList = vehicleRepository.findByBrand(brand);

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        double averageSpeed = vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .map(speed -> Double.parseDouble(speed.max_speed()))
                .mapToDouble(speed -> speed)
                .average()
                .orElse(0.0);

        return "A velocidade média é " + averageSpeed;
    }

    @Override
    public List<VehicleDto> findByFuelType(String type) {
        List<Vehicle> vehicleList = vehicleRepository.findByFuelType(type);

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        return vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> findByTransmission(String transmission) {
        List<Vehicle> vehicleList = vehicleRepository.findByTransmission(transmission);

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        return vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .collect(Collectors.toList());
    }

    public String findByAverageCapacity(String brand){
        List<Vehicle> vehicleList = vehicleRepository.findByBrand(brand);

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        Double averageCapacity = vehicleList.stream()
                .map(vehicle -> convertVehicleToDto(vehicle))
                .map(capacity -> capacity.passengers().doubleValue())
                .mapToDouble(capacity -> capacity)
                .average()
                .orElse(0.0);

        return "A capacidade média de pessoas nos veículos da marca " + brand + " é: " + averageCapacity;
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.delete(id);
    }

    @Override
    public List<VehicleDto> searchAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        if (vehicleList.isEmpty()) {
            throw new NotFoundException("No se encontró ningun auto en el sistema.");
        }

        return vehicleList.stream()
                .map(this::convertVehicleToDto)
                .collect(Collectors.toList());
    }

    private VehicleDto convertVehicleToDto(Vehicle v) {
        return new VehicleDto(
                v.getId(),
                v.getBrand(),
                v.getModel(),
                v.getRegistration(),
                v.getColor(),
                v.getYear(),
                v.getMax_speed(),
                v.getPassengers(),
                v.getFuel_type(),
                v.getTransmission(),
                v.getLength(),
                v.getWidth(),
                v.getWeight());
    }

    private Vehicle validateVehicle(Vehicle vehicle) {
        List<Vehicle> listId = vehicleRepository.findById(vehicle.getId());

        boolean existsId = listId.stream()
                .anyMatch(vehicle1 -> vehicle.getId().equals(vehicle1.getId()));

        if (existsId) {
            throw new AlreadyExistsException("El id del auto ya existe en el sistema.");
        }
        if (vehicle.getBrand() == null || vehicle.getBrand().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'brand' no puede ser nulo o vacío.");
        }

        if (vehicle.getModel() == null || vehicle.getModel().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'modelo' no puede ser nulo o vacío.");
        }
        if (vehicle.getRegistration() == null || vehicle.getRegistration().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'registration' no puede ser nulo o vacío.");
        }
        if (vehicle.getYear() == null) {
            throw new InvalidVehicleDataException("El campo 'year' no puede ser nulo o vacío.");
        }
        if (vehicle.getColor() == null || vehicle.getColor().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'color' no puede ser nulo o vacío.");
        }
        if (vehicle.getYear() == null || vehicle.getYear() == 0 || vehicle.getYear() > 2025) {
            throw new InvalidVehicleDataException("El campo 'year' no puede ser nulo o vacío.");
        }
        if (vehicle.getMax_speed() == null || vehicle.getMax_speed().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'max_speed' no puede ser nulo o vacío.");
        }
        if (vehicle.getPassengers() == null) {
            throw new InvalidVehicleDataException("El campo 'passengers' no puede ser nulo o vacío.");
        }
        if (vehicle.getFuel_type() == null || vehicle.getFuel_type().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'fuel_type' no puede ser nulo o vacío.");
        }
        if (vehicle.getTransmission() == null || vehicle.getTransmission().isEmpty()) {
            throw new InvalidVehicleDataException("El campo 'transmission' no puede ser nulo o vacío.");
        }
        if (vehicle.getLength() == null || vehicle.getLength() == 0) {
            throw new InvalidVehicleDataException("El campo 'lenght' no puede ser nulo o vacío.");
        }
        if (vehicle.getWeight() == null || vehicle.getWeight() == 0) {
            throw new InvalidVehicleDataException("El campo 'weight' no puede ser nulo o vacío.");
        }
        if (vehicle.getWidth() == null || vehicle.getWidth() == 0) {
            throw new InvalidVehicleDataException("El campo 'width' no puede ser nulo o vacío.");
        }
        return vehicle;
    }
}
