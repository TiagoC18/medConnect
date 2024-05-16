package project.medConnect.controller;

import project.medConnect.entity.Medic;
import project.medConnect.service.MedicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medic")
public class MedicController {

    @Autowired
    private MedicService medicService;

    @GetMapping("")
    public ResponseEntity<Object> getAllMedics() {
            
        List<Medic> medics = medicService.getAllMedics();

        if (medics.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(medics, HttpStatus.OK);
        }
            
    }

    @GetMapping("/{medicId}")
    public ResponseEntity<Object> getMedicById(@PathVariable Long medicId) {

        Medic medic = medicService.getMedicById(medicId);

        if (medic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(medic, HttpStatus.OK);
        }
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<Object> getMedicBySpecialty(@PathVariable String specialty) {

        List<Medic> medics = medicService.getMedicBySpecialty(specialty);

        if (medics.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(medics, HttpStatus.OK);
        }
    }
}