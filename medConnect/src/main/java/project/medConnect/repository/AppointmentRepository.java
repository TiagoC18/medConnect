package project.medConnect.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.specialty = :specialty " + "AND a.medic = :medic " +
            "AND a.appointmentTime = :appointmentTime " +
            "AND NOT EXISTS " +
            "(SELECT 1 FROM Appointment a2 WHERE a2.appointmentTime = :appointmentTime)")
    List<Appointment> findAvailableAppointmentsForSpecialtyAndMedic(
            String specialty,
            Medic medic,
            Date appointmentTime);
}
