package project.medConnect.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.entity.Patient;
import project.medConnect.repository.AppointmentRepository;

@DataJpaTest
@SuppressWarnings("deprecation")
public class AppointmentRepTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @DisplayName("Find All Appointments")
    public void testFindAllAppointments() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime("9:00-17:00");

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10, 0, 0));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment2.setStatus("Scheduled");

        entityManager.persist(medic);
        entityManager.persist(patient);
        entityManager.persist(appointment1);
        entityManager.persist(appointment2);
        entityManager.flush();

        List<Appointment> appointments = appointmentRepository.findAll();

        assertNotNull(appointments);
        assertEquals(2, appointments.size());
    }

    @Test
    @DisplayName("Post Appointment")
    public void testPostAppointment() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime("9:00-17:00");

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10, 0, 0));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment = new Appointment();
        appointment.setSpecialty("Cardiology");
        appointment.setMedic(medic);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment.setStatus("Scheduled");

        entityManager.persist(medic);
        entityManager.persist(patient);
        entityManager.persist(appointment);
        entityManager.flush();

        Appointment savedAppointment = appointmentRepository.save(appointment);
        assertNotNull(savedAppointment.getAppointmentId());
    }

    @Test
    @DisplayName("Find Appointment by Id")
    public void testFindAppointmentById() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime("9:00-17:00");

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10, 0, 0));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment2.setStatus("Scheduled");

        entityManager.persist(medic);
        entityManager.persist(patient);
        entityManager.persist(appointment1);
        entityManager.persist(appointment2);
        entityManager.flush();

        Appointment foundAppointment = appointmentRepository.findById(appointment1.getAppointmentId()).get();
        assertEquals(appointment1.getAppointmentId(), foundAppointment.getAppointmentId());
        assertEquals(appointment1.getSpecialty(), foundAppointment.getSpecialty());
        assertEquals(appointment1.getMedic(), foundAppointment.getMedic());
        assertEquals(appointment1.getPatient(), foundAppointment.getPatient());
        assertEquals(appointment1.getAppointmentTime(), foundAppointment.getAppointmentTime());
        assertEquals(appointment1.getStatus(), foundAppointment.getStatus());
    }    
    

    @Test
    @DisplayName("Find Available Appointments For Specialty And Medic")
    public void testFindAvailableAppointmentsForSpecialtyAndMedic() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime("9:00-17:00");

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10, 0, 0));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentTime(new Date(2021, 12, 12, 12, 0));
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentTime(new Date(2021, 12, 11, 12, 0));
        appointment2.setStatus("Scheduled");

        entityManager.persist(medic);
        entityManager.persist(patient);
        entityManager.persist(appointment1);
        entityManager.persist(appointment2);
        entityManager.flush();

        List<Appointment> appointments = appointmentRepository.findAvailableAppointmentsForSpecialtyAndMedic("Cardiology", medic, new Date(2021, 12, 10, 12, 0));
        assertNotNull(appointments);

        assertEquals(1, appointments.size());
        assertEquals("Cardiology", appointments.get(0).getSpecialty());
        assertEquals(medic, appointments.get(0).getMedic());
        assertEquals(patient, appointments.get(0).getPatient());
        assertEquals(new Date(2021, 12, 10, 12, 0), appointments.get(0).getAppointmentTime());
        assertEquals("Scheduled", appointments.get(0).getStatus());
    }
}


