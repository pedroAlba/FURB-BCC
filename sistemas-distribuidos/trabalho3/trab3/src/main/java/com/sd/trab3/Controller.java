package com.sd.trab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private final LicencaService service;

    public Controller(LicencaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity adquire(){
        try{
            return ResponseEntity.status(200).body(service.get());
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
