package project.medConnect.service;

import project.medConnect.entity.Medic;
import project.medConnect.repository.MedicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MedicService {

    @Autowired
    private MedicRepository medicRepository;

    public List<Medic> getAllMedics() {
        List<Medic> medics = medicRepository.findAll();
        return medics;
    }

    public Medic getMedicById(Long medicId) {
        Optional<Medic> medic = medicRepository.findById(medicId);
        if (medic.isPresent()) {
            return medic.get();
        } else {
            throw new NoSuchElementException("Medic with id " + medicId + " not found");
        }
    }

    public List<Medic> getMedicBySpecialty(String specialty) {
        List<Medic> medics = medicRepository.findMedicBySpecialty(specialty);
        return medics;
    }

    public Medic getServiceTime(Long medicId) {
        Optional<Medic> medic = medicRepository.findById(medicId);
        if (medic.isPresent()) {
            return medic.get();
        } else {
            throw new NoSuchElementException("Medic with id " + medicId + " not found");
        }
    }

    public Medic findMedicByName(String firstName, String lastName) {
        Medic medic = medicRepository.findMedicByName(firstName, lastName);
        return medic;
    }
}