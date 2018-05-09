package com.locadora.controller;

import com.locadora.model.User;
import com.locadora.model.Vehicle;
import com.locadora.repository.UserRepository;
import com.locadora.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public boolean doLogin(@Valid @RequestBody User u){
        Optional<User> usuario = userRepository.findAll().stream().filter(user -> u.getCpf().equals(user.getCpf())).findFirst();
        if(usuario.isPresent()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(u.getPassword(), usuario.get().getPassword());
        }
        return false;
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User u){
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return userRepository.save(u);
    }
}
