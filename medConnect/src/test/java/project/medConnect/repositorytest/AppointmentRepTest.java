package project.medConnect.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
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
        medic.setServiceTime(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentDay("2024-06-08");
        appointment1.setAppointmentTime("10h");
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentDay("2024-06-08");
        appointment2.setAppointmentTime("10h");
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
        medic.setServiceTime(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment = new Appointment();
        appointment.setSpecialty("Cardiology");
        appointment.setMedic(medic);
        appointment.setPatient(patient);
        appointment.setAppointmentDay("2024-06-08");
        appointment.setAppointmentTime("10h");
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
        medic.setServiceTime(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentDay("2024-06-08");
        appointment1.setAppointmentTime("10h");
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentDay("2024-06-08");
        appointment2.setAppointmentTime("10h");
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
    @DisplayName("Booked Appointments")
    public void testFindBookedAppointments() {
        Medic medic = new Medic();
        medic.setFirstName("John");
        medic.setLastName("Doe");
        medic.setEmail("johndoe@ua.pt");
        medic.setPhoneNumber("912345678");
        medic.setSpecialty("Cardiology");
        medic.setServiceTime(Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        Patient patient = new Patient();
        patient.setFirstName("David");
        patient.setLastName("Silva");
        patient.setDateOfBirth(new Date(1999, 7, 10));
        patient.setGender("Male");
        patient.setPhoneNumber("123456789");
        patient.setEmail("davidsilva@ua.pt");

        Appointment appointment1 = new Appointment();
        appointment1.setSpecialty("Cardiology");
        appointment1.setMedic(medic);
        appointment1.setPatient(patient);
        appointment1.setAppointmentDay("2024-06-08");
        appointment1.setAppointmentTime("10h");
        appointment1.setStatus("Scheduled");

        Appointment appointment2 = new Appointment();
        appointment2.setSpecialty("Dermatology");
        appointment2.setMedic(medic);
        appointment2.setPatient(patient);
        appointment2.setAppointmentDay("2024-06-08");
        appointment2.setAppointmentTime("11h");
        appointment2.setStatus("Scheduled");

        entityManager.persist(medic);
        entityManager.persist(patient);
        entityManager.persist(appointment1);
        entityManager.persist(appointment2);
        entityManager.flush();

        List<String> bookedAppointments = appointmentRepository.findBookedAppointments("Cardiology", medic, "2024-06-08");
        assertNotNull(bookedAppointments);
        assertEquals(2, bookedAppointments.size());
    }
}    