package project.medConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Patient;
import project.medConnect.entity.PatientAuth;

@Repository
public interface PatientAuthRepository extends JpaRepository<PatientAuth, Long> {

    @Query("SELECT pa.patient FROM PatientAuth pa WHERE pa.username = :username")
    Patient findPatientByUserName(String username);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN 'true' ELSE 'false' END FROM PatientAuth p WHERE p.username = :username AND p.password = :password")
    boolean checkPassword(String username, String password);
}
