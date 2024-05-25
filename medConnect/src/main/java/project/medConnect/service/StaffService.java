package project.medConnect.service;

import project.medConnect.entity.Staff;
import project.medConnect.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        List<Staff> staff = staffRepository.findAll();
        return staff;
    }

    public Staff getStaffById(Long staffId) {
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isPresent()) {
            return staff.get();
        } else {
            throw new NoSuchElementException("Staff with id " + staffId + " not found");
        }
    }
    
    public boolean checkPassword(String email, String password) {
        return staffRepository.checkPassword(email, password);
    }
    
}
