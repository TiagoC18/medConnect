package project.medConnect.servicetest;

import project.medConnect.entity.Staff;
import project.medConnect.repository.StaffRepository;
import project.medConnect.service.StaffService;

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
public class StaffServTest {
    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        reset(staffRepository);
    }

    @Test
    @DisplayName("Test Find All Staff")
    void testFindAllStaff() {
        Staff staff1 = new Staff("Maria", "Dolores", "mariadolores@ua.pt", "mdolores123");
        Staff staff2 = new Staff("John", "Doe", "johndoe@ua.pt", "john123");

        Mockito.when(staffRepository.findAll()).thenReturn(java.util.Arrays.asList(staff1, staff2));

        java.util.List<Staff> staffs = staffService.getAllStaff();

        assertThat(staffs.size()).isEqualTo(2);
        Mockito.verify(staffRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Test Check Password")
    void testCheckPassword() {
        Mockito.when(staffRepository.checkPassword("mariadolores@ua.pt", "mdolores123")).thenReturn(Boolean.TRUE);

        Boolean check = staffService.checkPassword("mariadolores@ua.pt", "mdolores123");

        assertThat(check).isEqualTo(Boolean.TRUE);
        Mockito.verify(staffRepository, Mockito.times(1)).checkPassword("mariadolores@ua.pt", "mdolores123");
    }
}
