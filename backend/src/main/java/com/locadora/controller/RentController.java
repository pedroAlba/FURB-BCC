package com.locadora.controller;

import com.locadora.exception.InvalidParametersException;
import com.locadora.exception.ResourceNotFoundException;
import com.locadora.model.Rent;
import com.locadora.model.RentDTO;
import com.locadora.model.User;
import com.locadora.model.Vehicle;
import com.locadora.repository.RentRepository;
import com.locadora.repository.UserRepository;
import com.locadora.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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
        User u = userRepository.findById(r.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", r.getUserId()));

        if (r.getStartDate().isAfter(r.getEndDate())){
            throw new InvalidParametersException("Data inicial não pode ser posterior a data final");
        }

        Optional<Rent> duplicateRent = rentRepository.findAll().stream().filter(re -> re.getVehicle().getId().equals(r.getVehicleId()))
                                                      .filter(re -> re.getStartDate().equals(r.getStartDate())
                                                                    &&
                                                                    re.getEndDate().equals(r.getEndDate()))
                                                      .findAny();

        duplicateRent.ifPresent(s -> {
            throw new InvalidParametersException("Veículo já está alugado nessa data" + s.toString());
        });

        Rent rent = new Rent();
        rent.setUser(u);
        rent.setVehicle(v);
        rent.setStartDate(r.getStartDate());
        rent.setEndDate(r.getEndDate());

        return rentRepository.save(rent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRent(@PathVariable(value = "id") Long rentId) {
        Rent persistedRent = rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("Locação", "id", rentId));
        rentRepository.delete(persistedRent);
        return ResponseEntity.ok().build();
    }
}
