package com.locadora.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.exception.ResourceNotFoundException;
import com.locadora.model.Brand;
import com.locadora.model.Vehicle;
import com.locadora.repository.BrandRepository;
import com.locadora.repository.RentRepository;
import com.locadora.repository.VehicleRepository;

@CrossOrigin(origins="**")
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final RentRepository rentRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository, BrandRepository brandRepository, RentRepository rentRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.rentRepository = rentRepository;
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
        
        rentRepository.findAll().stream().filter(r -> r.getVehicle().getId().equals(vehicleId))
        						.forEach(rent -> rentRepository.deleteById(rent.getId()));
        						
        vehicleRepository.delete(persisted);
        return ResponseEntity.ok().build();
    }
}
