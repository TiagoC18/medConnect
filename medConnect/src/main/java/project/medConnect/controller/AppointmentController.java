package project.medConnect.controller;

import project.medConnect.model.Appointment;
import project.medConnect.model.Patient;
import project.medConnect.model.Specialty;
import project.medConnect.repository.PatientRepository;
import project.medConnect.repository.SpecialtyRepository;
import project.medConnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/available")
    public ResponseEntity<List<Appointment>> getAvailableAppointments(
            @RequestParam Long specialtyId,
            @RequestParam Date start,
            @RequestParam Date end) {
        try {
            Specialty specialty = specialtyRepository.findById(specialtyId)
                    .orElseThrow(() -> new RuntimeException("Specialty not found"));
            List<Appointment> availableAppointments = appointmentService.findAvailableAppointments(specialty, start, end);
            return new ResponseEntity<>(availableAppointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@Valid @RequestBody Appointment appointment) {
        try {
            Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
            return new ResponseEntity<>(bookedAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationExceptions(jakarta.validation.ConstraintViolationException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> errors.append(violation.getMessage()).append(" "));
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/past/{patientId}")
    public ResponseEntity<?> getPastAppointments(@PathVariable Long patientId) {
        try {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            List<Appointment> pastAppointments = appointmentService.findPastAppointments(patient);
            return new ResponseEntity<>(pastAppointments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
