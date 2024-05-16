package project.medConnect.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // @Query("SELECT DISTINCT st FROM Medic m " +
    //         "CROSS JOIN UNNEST(m.serviceTime) st " +
    //         "WHERE m.specialty = :specialty " +
    //         "AND st NOT IN " +
    //         "(SELECT a.appointmentTime FROM Appointment a " +
    //         "WHERE a.appointmentDay = :appointmentDay " +
    //         "AND a.specialty = :specialty " +
    //         "AND a.medic = m)")
    // List<String> findAvailableHours(@Param("specialty") String specialty,
    //                                 @Param("appointmentDay") Date appointmentDay);

}
