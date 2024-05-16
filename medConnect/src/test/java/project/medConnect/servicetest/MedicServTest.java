package project.medConnect.servicetest;

import project.medConnect.entity.Medic;
import project.medConnect.repository.MedicRepository;
import project.medConnect.service.MedicService;

import java.util.Arrays;
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
public class MedicServTest {
    @Mock
    private MedicRepository medicRepository;

    @InjectMocks
    private MedicService medicService;

    @BeforeEach
    public void setUp() {
        reset(medicRepository);
    }

    @Test
    @DisplayName("Test Find All Medics")
    public void testFindAllMedics() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", "9:00-17:00");
        Medic medic2 = new Medic( "Jane", "Smith", "janesmith@ua.pt", "912345678", "Dermatology", "10:00-18:00");
        Medic medic3 = new Medic("David", "Silva", "davidsilva@ua.pt", "912345678", "Cardiology", "8:00-16:00");

        Mockito.when(medicRepository.findAll()).thenReturn(Arrays.asList(medic1, medic2, medic3));

        List<Medic> medics = medicService.getAllMedics();

        assertThat(medics.size()).isEqualTo(3);
        Mockito.verify(medicRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test Find Medic By Id")
    public void testFindMedicById() {
        Medic medic1 = new Medic("John", "Doe", "johndoe@ua.pt", "912345678", "Cardiology", "9:00-17:00");
        medic1.setMedicId(1L);

        Mockito.when(medicRepository.findById(1L)).thenReturn(java.util.Optional.of(medic1));

        Medic medic = medicService.getMedicById(1L);

        assertThat(medic).isEqualTo(medic1);
        Mockito.verify(medicRepository, Mockito.times(1)).findById(1L);
    }
    
}
