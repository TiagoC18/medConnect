package project.medConnect.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Medic;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
}
