package project.medconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.medconnect.entity.Medic;
import project.medconnect.service.MedicService;

import java.util.List;

@RestController
@RequestMapping("/api/medic")
public class MedicController {

    private final MedicService medicService;

    @Autowired
    public MedicController(MedicService medicService) {
        this.medicService = medicService;
    }

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

    @GetMapping("/{medicId}/serviceTime")
    public ResponseEntity<Object> getServiceTime(@PathVariable Long medicId) {
        Medic medic = medicService.getServiceTime(medicId);

        if (medic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(medic.getServiceTime(), HttpStatus.OK);
        }
    }

    @GetMapping("/name/{firstName}/{lastName}")
    public ResponseEntity<Object> findMedicByName(@PathVariable String firstName, @PathVariable String lastName) {
        Medic medic = medicService.findMedicByName(firstName, lastName);

        if (medic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(medic, HttpStatus.OK);
        }
    }
}
