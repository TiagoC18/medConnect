package project.medconnect.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.*;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;

class AppointmentTest {
    private Appointment appointment;
    private Medic medic;
    private Patient patient;

    @BeforeEach
    void setUp() {
        medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        appointment = new Appointment(patient, "Specialty", medic, "2024-06-08", "10h", "Scheduled", null);
    }

    @AfterEach
    void tearDown() {
        appointment = null;
        medic = null;
        patient = null;
    }

    @Test
    void testGetPatient() {
        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void testSetPatient() {
        Patient newPatient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        appointment.setPatient(newPatient);
        assertEquals(newPatient, appointment.getPatient());
    }

    @Test
    void testGetSpecialty() {
        assertEquals("Specialty", appointment.getSpecialty());
    }

    @Test
    void testSetSpecialty() {
        appointment.setSpecialty("New Specialty");
        assertEquals("New Specialty", appointment.getSpecialty());
    }

    @Test
    void testGetMedic() {
        assertEquals(medic, appointment.getMedic());
    }

    @Test
    void testSetMedic() {
        Medic newMedic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        appointment.setMedic(newMedic);
        assertEquals(newMedic, appointment.getMedic());
    }

    @Test
    void testGetAppointmentDay() {
        assertEquals("2024-06-08", appointment.getAppointmentDay());
    }

    @Test
    void testSetAppointmentDay() {
        appointment.setAppointmentDay("2024-06-08");
        assertEquals("2024-06-08", appointment.getAppointmentDay());
    }

    @Test
    void testGetAppointmentTime() {
        assertEquals("10h", appointment.getAppointmentTime());
    }

    @Test
    void testSetAppointmentTime() {
        appointment.setAppointmentTime("11h");
        assertEquals("11h", appointment.getAppointmentTime());
    }

    @Test
    void testGetStatus() {
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    void testSetStatus() {
        appointment.setStatus("Cancelled");
        assertEquals("Cancelled", appointment.getStatus());
    }

    @Test
    void testGetSenha() {
        assertNull(appointment.getSenha());
    }

    @Test
    void testSetSenha() {
        appointment.setSenha(1);
        assertEquals(1, appointment.getSenha());
    }
}
