package project.medconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.medconnect.entity.Staff;
import project.medconnect.repository.StaffRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new NoSuchElementException("Staff with id " + staffId + " not found"));
    }

    public boolean checkPassword(String email, String password) {
        return staffRepository.checkPassword(email, password);
    }
}
