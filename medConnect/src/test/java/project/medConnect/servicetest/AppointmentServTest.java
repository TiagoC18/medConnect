package project.medConnect.servicetest;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.entity.Patient;
import project.medConnect.repository.AppointmentRepository;
import project.medConnect.repository.PatientRepository;
import project.medConnect.service.AppointmentService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@Deprecated
@ExtendWith(MockitoExtension.class)
class AppointmentServTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        reset(appointmentRepository);
        reset(patientRepository);
    }

    @Test
    @DisplayName("Get all appointments")
    void testGetAllAppointments() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
    
        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

        List<Appointment> appointments = appointmentService.getAppointments();

        assertThat(appointments).hasSize(2);
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Post an Appointment")
    void testPostAnAppointment() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);

        when(appointmentRepository.save(appointment1)).thenReturn(appointment1);

        Appointment appointmentSaved = appointmentService.addAppointment(appointment1);

        assertThat(appointmentSaved).isEqualTo(appointment1);
        verify(appointmentRepository, times(1)).save(appointment1);
    }

    @Test
    @DisplayName("Get Booked Appointments")
    void testGetBookedAppointments() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        when(appointmentRepository.findBookedAppointments("Cardiology", medic, "2024-06-08"))
                .thenReturn(Arrays.asList(appointment1.getAppointmentTime(), appointment2.getAppointmentTime()));

        List<String> bookedAppointments = appointmentService.getBookedAppointments("Cardiology", medic, "2024-06-08");

        assertThat(bookedAppointments)
                .isNotNull()
                .hasSize(2)
                .contains("10h", "11h");
        verify(appointmentRepository, times(1)).findBookedAppointments("Cardiology", medic, "2024-06-08");
    }

    @Test
    @DisplayName("Get Appointments by Patient")
    void testGetAppointmentsByPatient() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        patient.setPatientId(1L);

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(appointmentRepository.findAppointmentsByPatient(patient)).thenReturn(Arrays.asList(appointment1, appointment2));

        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(1L);

        assertThat(appointments)
                .isNotNull()
                .hasSize(2)
                .contains(appointment1, appointment2);
        verify(appointmentRepository, times(1)).findAppointmentsByPatient(patient);
    }

    @Test
    @DisplayName("Get Appointments Scheduled")
    void testGetAppointmentsScheduled() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Cancelled", null);

        List<Appointment> appointments = Arrays.asList(appointment1);

        when(appointmentRepository.findAppointmentsScheduled()).thenReturn(appointments);

        List<Appointment> appointmentsScheduled = appointmentService.getAppointmentsScheduled();

        assertThat(appointmentsScheduled)
                .isNotNull()
                .hasSize(1)
                .contains(appointment1);
        verify(appointmentRepository, times(1)).findAppointmentsScheduled();
    }

    @Test
    @DisplayName("Get Appointments Waiting")
    void testGetAppointmentsWaiting() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Waiting", null);

        List<Appointment> appointments = Arrays.asList(appointment2);

        when(appointmentRepository.findAppointmentsWaiting()).thenReturn(appointments);

        List<Appointment> appointmentsWaiting = appointmentService.getAppointmentsWaiting();

        assertThat(appointmentsWaiting)
                .isNotNull()
                .hasSize(1)
                .contains(appointment2);
        verify(appointmentRepository, times(1)).findAppointmentsWaiting();
    }

    @Test
    @DisplayName("Get Appointments Called")
    void testGetAppointmentsCalled() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Scheduled", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Called", null);

        List<Appointment> appointments = Arrays.asList(appointment2);

        when(appointmentRepository.findAppointmentsCalled()).thenReturn(appointments);

        List<Appointment> appointmentsCalled = appointmentService.getAppointmentsCalled();

        assertThat(appointmentsCalled)
                .isNotNull()
                .hasSize(1)
                .contains(appointment2);
        verify(appointmentRepository, times(1)).findAppointmentsCalled();
    }

    @Test
    @DisplayName("Get Appointments Done")
    void testGetAppointmentsDone() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Done", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Called", null);

        List<Appointment> appointments = Arrays.asList(appointment1);

        when(appointmentRepository.findAppointmentsDone()).thenReturn(appointments);

        List<Appointment> appointmentsDone = appointmentService.getAppointmentsDone();

        assertThat(appointmentsDone)
                .isNotNull()
                .hasSize(1)
                .contains(appointment1);
        verify(appointmentRepository, times(1)).findAppointmentsDone();
    }

    @Test
    @DisplayName("Update Appointment Status")
    void testUpdateAppointmentStatus() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Done", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Called", null);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment1));
        when(appointmentRepository.save(appointment1)).thenReturn(appointment1);

        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(1L, "Done");

        assertThat(updatedAppointment)
                .isNotNull()
                .extracting(Appointment::getStatus)
                .isEqualTo("Done");
        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(appointment1);
    }

    @Test
    @DisplayName("Delete Appointment")
    void testDeleteAppointment() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Waiting", null);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Called", null);
        appointment1.setAppointmentId(1L);
        
        when(appointmentRepository.existsById(1L)).thenReturn(true);
        assertThat(appointmentRepository.existsById(1L)).isTrue();

        doNothing().when(appointmentRepository).deleteById(1L);
        appointmentService.deleteAppointment(1L);
        verify(appointmentRepository, times(1)).deleteById(1L);
        
        when(appointmentRepository.existsById(1L)).thenReturn(false);
        assertThat(appointmentRepository.existsById(1L)).isFalse();
    }

    @Test
    @DisplayName("Reset all Senha")
    void testResetAllSenha() {
        Medic medic = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Patient patient = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        Appointment appointment1 = new Appointment(patient, "Cardiology", medic, "2024-06-08", "10h", "Waiting", 2);
        Appointment appointment2 = new Appointment(patient, "Cardiology", medic, "2024-07-08", "11h", "Called", 1);
        
        when(appointmentRepository.findMaxSenha()).thenReturn(2);
        assertThat(appointmentRepository.findMaxSenha()).isEqualTo(2);

        doNothing().when(appointmentRepository).resetAllSenha();
        appointmentService.resetAllSenha();
        verify(appointmentRepository, times(1)).resetAllSenha();

        when(appointmentRepository.findMaxSenha()).thenReturn(null);
        assertThat(appointmentRepository.findMaxSenha()).isNull();
    }

} 
