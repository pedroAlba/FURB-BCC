package com.sd.trab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

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
            Licenca l = service.get();
            l.setObtido(Instant.now());
            return ResponseEntity.status(200).body(l);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity returnLicence(@PathVariable(value = "id") Integer licID){
        service.returnLic(new Licenca(licID));
        return ResponseEntity.ok().build();
    }
}
