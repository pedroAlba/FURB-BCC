package com.locadora.controller;

import com.locadora.exception.ResourceNotFoundException;
import com.locadora.model.Brand;
import com.locadora.model.Vehicle;
import com.locadora.repository.BrandRepository;
import com.locadora.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="**")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    @PostMapping
    public Vehicle saveVehicle(@Valid @RequestBody Vehicle v){
        Optional<Brand> b = brandRepository.findById(v.getBrand().getId());
        b.ifPresent(v::setBrand);
        return vehicleRepository.save(v);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable(value = "id") Long vehicleId, @Valid @RequestBody Vehicle updatedVehicle){
        Vehicle persisted = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Veículo", "id", vehicleId));

        persisted.setName(updatedVehicle.getName());
        persisted.setCategory(updatedVehicle.getCategory());
        persisted.setCharacteristics(updatedVehicle.getCharacteristics());
        persisted.setDoors(updatedVehicle.getDoors());
        persisted.setLocation(updatedVehicle.getLocation());
        persisted.setModel(updatedVehicle.getModel());
        persisted.setRentalValue(updatedVehicle.getRentalValue());
        persisted.setYear(updatedVehicle.getYear());
        persisted.setImageURL(updatedVehicle.getImageURL());
        persisted.setBrand(updatedVehicle.getBrand());

        return vehicleRepository.save(persisted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Long vehicleId) {
        Vehicle persisted = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Veículo", "id", vehicleId));
        vehicleRepository.delete(persisted);
        return ResponseEntity.ok().build();
    }
}
