package project.medconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.medconnect.entity.Patient;
import project.medconnect.repository.PatientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient with id " + patientId + " not found"));
    }

    public Patient getPatientByEmail(String email) {
        return Optional.ofNullable(patientRepository.findPatientByEmail(email))
                .orElseThrow(() -> new NoSuchElementException("Patient with email " + email + " not found"));
    }

    public boolean checkPassword(String email, String password) {
        return patientRepository.checkPassword(email, password);
    }
}
