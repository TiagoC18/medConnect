package project.medconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medconnect.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT COUNT(s) > 0 FROM Staff s WHERE s.email = :email AND s.password = :password")
    boolean checkPassword(String email, String password);

}
