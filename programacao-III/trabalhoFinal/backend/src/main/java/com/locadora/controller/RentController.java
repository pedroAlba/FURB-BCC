package com.locadora.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
import com.locadora.model.Rent;
import com.locadora.model.RentDTO;
import com.locadora.model.User;
import com.locadora.model.Vehicle;
import com.locadora.repository.RentRepository;
import com.locadora.repository.UserRepository;
import com.locadora.repository.VehicleRepository;

@CrossOrigin(origins="**")
@RestController
@RequestMapping("/api/rent")
public class RentController {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public RentController(RentRepository rentRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public List<Rent> getAll(){
        return rentRepository.findAll();
    }

    @PostMapping
    public Rent doRent(@RequestBody RentDTO r) {

        Vehicle v = vehicleRepository.findById(r.getVehicleId()).orElseThrow(() -> new ResourceNotFoundException("Veículo", "id", r.getVehicleId()));
        User u = userRepository.findAll().stream().filter(user -> user.getUsername().equals(r.getUserName())).findAny().orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", r.getUserName()));

        Rent rent = new Rent();
        rent.setUser(u);
        rent.setVehicle(v);
        rent.setDate(r.getDate());

        return rentRepository.save(rent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRent(@PathVariable(value = "id") Long rentId) {
        Rent persistedRent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("Locação", "id", rentId));
        rentRepository.delete(persistedRent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRent(@PathVariable(value="id") Long rentId, @RequestBody RentDTO updatedRent) {
    	Rent persistedRent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("Reserva", "id", rentId));
    	persistedRent.setDate(updatedRent.getDate());
    	return ResponseEntity.ok().body(rentRepository.save(persistedRent));
    }
    
    @GetMapping("/days/{id}")
    public ResponseEntity getUnavailableDays(@PathVariable(value = "id") Long vehicleId){
        List<Rent> vehicleRents = rentRepository.findAll().stream().filter(r -> r.getVehicle().getId().equals(vehicleId)).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return ResponseEntity.ok().body(vehicleRents.stream().map(Rent::getDate).map(d -> d.format(formatter)).collect(Collectors.toList()));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getUserRents(@PathVariable(value = "username") String username){
        return ResponseEntity.ok().body(rentRepository.findAll().stream().filter(r -> r.getUser().getUsername().equals(username)));
    }
}
