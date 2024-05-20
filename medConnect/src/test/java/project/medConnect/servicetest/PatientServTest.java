package project.medConnect.servicetest;

import project.medConnect.entity.Patient;
import project.medConnect.repository.PatientRepository;
import project.medConnect.service.PatientService;

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
import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Deprecated
public class PatientServTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        reset(patientRepository);
    }

    @Test
    @DisplayName("Test Find All Patients")
    public void testFindAllPatients() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
        Patient patient2 = new Patient("John", "Doe", new Date(2003, 05, 15) , "Male", "123456789", "123456789", "johndoe@ua.pt");
        Patient patient3 = new Patient("Jane", "Smith", new Date(1995, 02, 20) ,"Female", "123456789", "123456789", "janesmith@ua.pt");

        Mockito.when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2, patient3));

        List<Patient> patients = patientService.getAllPatients();

        assertThat(patients.size()).isEqualTo(3);
        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test Find Patient By Id")
    public void testFindPatientById() {
        Patient patient1 = new Patient("David", "Silva", new Date(1999, 07, 10) , "Male", "123456789", "123456789", "davidsilva@ua.pt");
        patient1.setPatientId(1L);

        Mockito.when(patientRepository.findById(1L)).thenReturn(java.util.Optional.of(patient1));

        Patient patient = patientService.getPatientById(1L);

        assertThat(patient).isEqualTo(patient1);
        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);
    }    
}
