package project.medconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.medconnect.entity.Staff;
import project.medconnect.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllStaff() {
        List<Staff> staff = staffService.getAllStaff();

        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(staff, HttpStatus.OK);
        }
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<Object> getStaffById(@PathVariable Long staffId) {
        Staff staff = staffService.getStaffById(staffId);

        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(staff, HttpStatus.OK);
        }
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestParam String email, @RequestParam String password) {
        boolean result = staffService.checkPassword(email, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
