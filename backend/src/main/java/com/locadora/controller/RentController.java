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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User doRent(@RequestBody RentDTO r){

        Vehicle v = vehicleRepository.findById(r.getVehicleId()).orElseThrow(() -> new ResourceNotFoundException("Veículo", "id", r.getVehicleId()));
        User u = userRepository.findById(r.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", r.getUserId()));

        if (r.getStartDate().after(r.getEndDate())){
            throw new InvalidParametersException("Data inicial não pode ser posterior a data final");
        }

        //TODO: Verificar se esse veículo não está reservado nessa data
        return null;
    }

}
