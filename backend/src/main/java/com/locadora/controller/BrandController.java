package com.locadora.controller;

import com.locadora.model.Brand;
import com.locadora.model.Vehicle;
import com.locadora.repository.BrandRepository;
import com.locadora.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="**")
@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    @PostMapping
    public Brand saveBrand(@Valid @RequestBody Brand b){
        return brandRepository.save(b);
    }
}
