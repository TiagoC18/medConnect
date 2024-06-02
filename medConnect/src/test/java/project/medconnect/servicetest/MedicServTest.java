package project.medconnect.servicetest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import project.medconnect.entity.Medic;
import project.medconnect.repository.MedicRepository;
import project.medconnect.service.MedicService;

import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MedicServTest {
    @Mock
    private MedicRepository medicRepository;

    @InjectMocks
    private MedicService medicService;

    @BeforeEach
    void setUp() {
        reset(medicRepository);
    }

    @Test
    @DisplayName("Test Find All Medics")
    void testFindAllMedics() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        Medic medic2 = new Medic( "Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", Arrays.asList("10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h", "18h"));
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Dermatology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));

        Mockito.when(medicRepository.findAll()).thenReturn(Arrays.asList(medic1, medic2, medic3));

        List<Medic> medics = medicService.getAllMedics();

        assertThat(medics).hasSize(3);
        Mockito.verify(medicRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test Find Medic By Id")
    void testFindMedicById() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        medic1.setMedicId(1L);

        Mockito.when(medicRepository.findById(1L)).thenReturn(java.util.Optional.of(medic1));

        Medic medic = medicService.getMedicById(1L);

        assertThat(medic).isEqualTo(medic1);
        Mockito.verify(medicRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test Find Medic By Specialty")
    void testFindMedicBySpecialty() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        Mockito.when(medicRepository.findMedicBySpecialty("Cardiology")).thenReturn(Arrays.asList(medic1));

        List<Medic> medics = medicService.getMedicBySpecialty("Cardiology");

        assertThat(medics).hasSize(1);
        Mockito.verify(medicRepository, Mockito.times(1)).findMedicBySpecialty("Cardiology");
    }

    @Test
    @DisplayName("Test Find Service Time")
    void testFindServiceTime() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        Mockito.when(medicRepository.findById(1L)).thenReturn(java.util.Optional.of(medic1));

        Medic medic = medicService.getServiceTime(1L);

        assertThat(medic).isEqualTo(medic1);
        Mockito.verify(medicRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test Find Medic By Name")
    void testFindMedicByName() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", Arrays.asList("9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h", "17h"));
        
        Mockito.when(medicRepository.findMedicByName("John", "Doe")).thenReturn(medic1);

        Medic medic = medicService.findMedicByName("John", "Doe");

        assertThat(medic).isEqualTo(medic1);
        Mockito.verify(medicRepository, Mockito.times(1)).findMedicByName("John", "Doe");
    }
}
