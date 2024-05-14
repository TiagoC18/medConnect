package project.medConnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull(message = "Patient is required")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    @NotNull(message = "Staff is required")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    @NotNull(message = "Specialty is required")
    private Specialty specialty;

    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment time must be in the future")
    private Date appointmentTime;

    private int duration;

    @NotNull(message = "Status is required")
    private String status;

 

    public Appointment(Long appointmentId, Patient patient, Staff staff, Specialty specialty, Date appointmentTime, int duration, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.staff = staff;
        this.specialty = specialty;
        this.appointmentTime = appointmentTime;
        this.duration = duration;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}