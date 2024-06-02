package project.medConnect.service;

import project.medConnect.entity.Medic;
import project.medConnect.repository.MedicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicService {

    private final MedicRepository medicRepository;

    @Autowired
    public MedicService(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    public List<Medic> getAllMedics() {
        return medicRepository.findAll();
    }

    public Medic getMedicById(Long medicId) {
        return medicRepository.findById(medicId)
                .orElseThrow(() -> new NoSuchElementException("Medic with id " + medicId + " not found"));
    }

    public List<Medic> getMedicBySpecialty(String specialty) {
        return medicRepository.findMedicBySpecialty(specialty);
    }

    public Medic getServiceTime(Long medicId) {
        return medicRepository.findById(medicId)
                .orElseThrow(() -> new NoSuchElementException("Medic with id " + medicId + " not found"));
    }

    public Medic findMedicByName(String firstName, String lastName) {
        return medicRepository.findMedicByName(firstName, lastName);
    }
}
