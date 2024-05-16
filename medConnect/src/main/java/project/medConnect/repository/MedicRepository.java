package project.medConnect.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Medic;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {

    @Query("SELECT m FROM Medic m WHERE m.specialty = :specialty")
    List<Medic> findMedicBySpecialty(String specialty);

}
