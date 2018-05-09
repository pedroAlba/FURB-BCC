package com.locadora.controller;

import com.locadora.model.User;
import com.locadora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public boolean doLogin(@RequestBody User u){
        Optional<User> usuario = userRepository.findAll().stream().filter(user -> u.getUsername().equals(user.getUsername())).findFirst();
        if(usuario.isPresent()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(u.getPassword(), usuario.get().getPassword());
        }
        return false;
   }
}
