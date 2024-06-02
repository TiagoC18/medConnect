package project.medConnect.controller;

import project.medConnect.entity.Patient;
import project.medConnect.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Object> getPatientByEmail(@PathVariable String email) {
        Patient patient = patientService.getPatientByEmail(email);

        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestParam String email, @RequestParam String password) {
        boolean result = patientService.checkPassword(email, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
