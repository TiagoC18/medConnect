package project.medConnect.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    Patient findPatientByEmail(String email);

    @Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.email = :email AND p.password = :password")
    boolean checkPassword(String email, String password);

}