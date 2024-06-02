package project.medConnect.service;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.entity.Patient;
import project.medConnect.exception.AppointmentNotFoundException;
import project.medConnect.repository.AppointmentRepository;
import project.medConnect.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<String> getBookedAppointments(String specialty, Medic medic, String day) {
        return appointmentRepository.findBookedAppointments(specialty, medic, day);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            return appointmentRepository.findAppointmentsByPatient(patient);
        } else {
            return List.of();
        }
    }

    public List<Appointment> getAppointmentsScheduled() {
        return appointmentRepository.findAppointmentsScheduled();
    }

    public List<Appointment> getAppointmentsWaiting() {
        return appointmentRepository.findAppointmentsWaiting();
    }

    public List<Appointment> getAppointmentsCalled() {
        return appointmentRepository.findAppointmentsCalled();
    }

    public List<Appointment> getAppointmentsDone() {
        return appointmentRepository.findAppointmentsDone();
    }

    public Appointment updateAppointmentStatus(Long appointmentId, String newStatus) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            String currentStatus = appointment.getStatus();

            if (currentStatus.equals("Scheduled") && newStatus.equals("Waiting")) {
                Integer lastSenha = appointmentRepository.findMaxSenha();
                int nextSenha = (lastSenha != null) ? lastSenha + 1 : 1;
                appointment.setSenha(nextSenha);
            }

            appointment.setStatus(newStatus);
            return appointmentRepository.save(appointment);
        } else {
            throw new AppointmentNotFoundException("Appointment not found");
        }
    }

    public void deleteAppointment(Long appointmentId) {
        if (appointmentRepository.existsById(appointmentId)) {
            appointmentRepository.deleteById(appointmentId);
        } else {
            throw new AppointmentNotFoundException("Appointment not found");
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetAllSenha() {
        appointmentRepository.resetAllSenha();
    }
}
