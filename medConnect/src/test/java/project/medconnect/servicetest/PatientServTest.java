package project.medconnect.servicetest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import project.medconnect.entity.Patient;
import project.medconnect.repository.PatientRepository;
import project.medconnect.service.PatientService;

import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Deprecated
class PatientServTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        reset(patientRepository);
    }

    @Test
    @DisplayName("Test Find All Patients")
    void testFindAllPatients() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        Patient patient2 = new Patient("John", "Doe", new Date(2003, 5, 15), "Male", "123456789", "123456789", "johndoe@ua.pt", "john123");
        Patient patient3 = new Patient("Jane", "Smith", new Date(1995, 2, 20), "Female", "123456789", "123456789", "janesmith@ua.pt", "jane123");

        Mockito.when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2, patient3));

        List<Patient> patients = patientService.getAllPatients();

        assertThat(patients).hasSize(3);
        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test Add Patient")
    void testAddPatient() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");

        patientService.addPatient(patient1);

        Mockito.verify(patientRepository, Mockito.times(1)).save(patient1);
    }

    @Test
    @DisplayName("Test Find Patient By Id")
    void testFindPatientById() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        patient1.setPatientId(1L);

        Mockito.when(patientRepository.findById(1L)).thenReturn(java.util.Optional.of(patient1));

        Patient patient = patientService.getPatientById(1L);

        assertThat(patient).isEqualTo(patient1);
        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);
    }    

    @Test
    @DisplayName("Test Find Patient By Email")
    void testFindPatientByEmail() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 7, 10), "Male", "123456789", "123456789", "davidsilva@ua.pt", "password");
        patient1.setPatientId(1L);

        Mockito.when(patientRepository.findPatientByEmail("davidsilva@ua.pt")).thenReturn(patient1);

        Patient patient = patientService.getPatientByEmail("davidsilva@ua.pt");

        assertThat(patient).isEqualTo(patient1);
        Mockito.verify(patientRepository, Mockito.times(1)).findPatientByEmail("davidsilva@ua.pt");
    }

    @Test
    @DisplayName("Test Check Password")
    void testCheckPassword() {
        Mockito.when(patientRepository.checkPassword("davidsilva@ua.pt","password")).thenReturn(Boolean.TRUE);

        boolean result = patientService.checkPassword("davidsilva@ua.pt","password");

        assertThat(result).isTrue();
        Mockito.verify(patientRepository, Mockito.times(1)).checkPassword("davidsilva@ua.pt","password");
    }
}
