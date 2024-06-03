package project.medconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medconnect.entity.Medic;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {

    @Query("SELECT m FROM Medic m WHERE m.specialty = :specialty")
    List<Medic> findMedicBySpecialty(String specialty);

    @Query("SELECT m FROM Medic m WHERE m.firstName = :firstName AND m.lastName = :lastName")
    Medic findMedicByName(String firstName, String lastName);
    

}
