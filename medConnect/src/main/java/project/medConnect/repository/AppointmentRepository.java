package project.medConnect.repository;

import project.medConnect.model.Appointment;
import project.medConnect.model.Patient;
import project.medConnect.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findBySpecialtyAndAppointmentTimeBetween(Specialty specialty, Date start, Date end);
    List<Appointment> findByPatientAndAppointmentTimeBefore(Patient patient, Date now);
}

