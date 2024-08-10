package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.app.dto.VaccineDto;
import com.app.service.VaccineService;

@CrossOrigin("*")
@RestController
@RequestMapping("/vaccines")
public class VaccineController {

	@Autowired
    private VaccineService vaccineService;

    @PostMapping("/addvaccine")
    public ResponseEntity<?> addNewVaccine(@Valid @RequestBody VaccineDto vaccineDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vaccineService.addNewVaccine(vaccineDto));
    }

    @GetMapping("/getallvaccines")
    public ResponseEntity<?> getAllVaccines() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vaccineService.getAllVaccines());
    }

    @PutMapping("/updatevaccine/{id}")
    public ResponseEntity<?> updateVaccine(@PathVariable Long id,
                                           @Valid @RequestBody VaccineDto vaccineUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vaccineService.updateVaccine(id, vaccineUpdateDto));
    }

    @DeleteMapping("/deletevaccine/{id}")
    public ResponseEntity<?> deleteVaccine(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vaccineService.deleteVaccine(id));
    }
    
}
