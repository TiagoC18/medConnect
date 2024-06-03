package project.medconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a.appointmentTime FROM Appointment a WHERE a.medic.specialty = :specialty AND a.medic = :medic AND a.appointmentDay = :day")
    List<String> findBookedAppointments(@Param("specialty") String specialty, 
                                    @Param("medic") Medic medic, 
                                    @Param("day") String day);

                                    
    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient")
    List<Appointment> findAppointmentsByPatient(@Param("patient") Patient patient);

    //find appointments scheduled
    @Query("SELECT a FROM Appointment a WHERE a.status = 'Scheduled'")
    List<Appointment> findAppointmentsScheduled();    

    //find appointments waiting
    @Query("SELECT a FROM Appointment a WHERE a.status = 'Waiting'")
    List<Appointment> findAppointmentsWaiting();

    //find appointments called
    @Query("SELECT a FROM Appointment a WHERE a.status = 'Called'")
    List<Appointment> findAppointmentsCalled();

    //find appointments done
    @Query("SELECT a FROM Appointment a WHERE a.status = 'Done'")
    List<Appointment> findAppointmentsDone();

    @Query("SELECT MAX(a.senha) FROM Appointment a")
    Integer findMaxSenha();

    @Modifying
    @Query("UPDATE Appointment a SET a.senha = null")
    void resetAllSenha();
}

