package project.medConnect.service;

import project.medConnect.entity.Appointment;
import project.medConnect.entity.Medic;
import project.medConnect.entity.Patient;
import project.medConnect.repository.AppointmentRepository;
import project.medConnect.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public Appointment addAppointment(Appointment appointment) {
        Appointment newAppointment = appointmentRepository.save(appointment);
        return newAppointment;
    }

    public List<String> getBookedAppointments(String specialty, Medic medic, String day) {
        List<String> bookedAppointments = appointmentRepository.findBookedAppointments(specialty, medic, day);
        return bookedAppointments;
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        List<Appointment> appointments = appointmentRepository.findAppointmentsByPatient(patient);
        return appointments;
    }

}
