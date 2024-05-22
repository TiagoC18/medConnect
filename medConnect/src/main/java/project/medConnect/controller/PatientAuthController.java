package project.medConnect.controller;

import project.medConnect.entity.PatientAuth;
import project.medConnect.service.PatientAuthService;
import project.medConnect.entity.Patient;
import project.medConnect.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/patient")
public class PatientAuthController {

    @Autowired
    private PatientAuthService patientAuthService;

    @GetMapping("/all")
    public List<PatientAuth> getAllPatientsAuth() {
        return patientAuthService.getAllPatientsAuth();
    }

    @GetMapping("/{username}")
    public Patient getPatientByUserName(@PathVariable String username) {
        return patientAuthService.getPatientByUserName(username);
    }

    @PostMapping("/{username}/{password}")
    public ResponseEntity<Boolean> checkPassword(@RequestBody PatientAuth patientAuth) {
        boolean result = patientAuthService.checkPassword(patientAuth.getUsername(), patientAuth.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    
}
