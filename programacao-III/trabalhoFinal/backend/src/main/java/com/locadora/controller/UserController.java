package com.locadora.controller;

import com.locadora.exception.ResourceNotFoundException;
import com.locadora.model.User;
import com.locadora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="**")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody User u){
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return userRepository.save(u);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User updatedUser){
        User persistedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", userId));
        persistedUser.setUsername(updatedUser.getUsername());
        persistedUser.setName(updatedUser.getName());
        return userRepository.save(persistedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User persistedUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", userId));
        userRepository.delete(persistedUser);
        return ResponseEntity.ok().build();
    }

}
