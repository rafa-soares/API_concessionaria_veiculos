package com.bootcampW22.EjercicioGlobal.repository;

import com.bootcampW22.EjercicioGlobal.entity.Vehicle;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VehicleRepositoryImpl implements IVehicleRepository {

    private List<Vehicle> listOfVehicles = new ArrayList<>();

    public VehicleRepositoryImpl() throws IOException {
        loadDataBase();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        listOfVehicles.add(vehicle);
        saveToJson();
        return vehicle;
    }

    @Override
    public List<Vehicle> findById(Long id) {

        return listOfVehicles.stream()
                .filter(vehicle -> id.equals(vehicle.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findAll() {
        return listOfVehicles;
    }

    @Override
    public List<Vehicle> findByBrandAndPeriod(String brand, Integer start, Integer end) {
        return listOfVehicles.stream()
                .filter((vehicle) -> {
                    Boolean resultBrand = brand.equals(vehicle.getBrand());

                    Integer year = vehicle.getYear(); // Obtendo o ano do veículo
                    Boolean resultPeriod = year >= start && year <= end; // Verificando se está entre o intervalo

                    return resultBrand && resultPeriod;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        listOfVehicles.removeIf(vehicle -> id.equals(vehicle.getId()));
    }


    private void loadDataBase() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Vehicle> vehicles;

        file = ResourceUtils.getFile("classpath:vehicles_100.json");
        vehicles = objectMapper.readValue(file, new TypeReference<List<Vehicle>>() {
        });

        listOfVehicles = vehicles;
    }

    private void saveToJson() { // Persitência dos dados do JSON
        try {
            File file = ResourceUtils.getFile("classpath:vehicles_100.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, this.listOfVehicles);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
