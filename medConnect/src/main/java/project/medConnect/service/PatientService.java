package project.medConnect.service;

import project.medConnect.entity.Patient;
import project.medConnect.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public Patient getPatientById(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new NoSuchElementException("Patient with id " + patientId + " not found");
        }
    }

    public Patient getPatientByEmail(String email) {
        Patient patient = patientRepository.findPatientByEmail(email);
        if (patient != null) {
            return patient;
        } else {
            throw new NoSuchElementException("Patient with username " + email + " not found");
        }
    }

    public boolean checkPassword(String email, String password) {
        return patientRepository.checkPassword(email, password);
    }

}