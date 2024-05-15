package project.medConnect.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}