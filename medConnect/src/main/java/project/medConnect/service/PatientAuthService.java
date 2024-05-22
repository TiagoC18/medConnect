package project.medConnect.service;

import project.medConnect.entity.PatientAuth;
import project.medConnect.repository.PatientAuthRepository;
import project.medConnect.entity.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientAuthService {

    @Autowired
    private PatientAuthRepository patientAuthRepository;

    public List<PatientAuth> getAllPatientsAuth() {
        List<PatientAuth> patients = patientAuthRepository.findAll();
        return patients;
    }


    public Patient getPatientByUserName(String username) {
        Patient patient = patientAuthRepository.findPatientByUserName(username);
        if (patient != null) {
            return patient;
        } else {
            throw new NoSuchElementException("Patient with username " + username + " not found");
        }
    }

    public boolean checkPassword(String username, String password) {
        return patientAuthRepository.checkPassword(username, password);
    }

}