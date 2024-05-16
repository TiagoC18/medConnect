package project.medConnect.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull(message = "Patient is required")
    private Patient patient;

    @NotNull(message = "Specialty is required")
    private String specialty;

    @ManyToOne
    @JoinColumn(name = "medic_id")
    @NotNull(message = "Medic is required")
    private Medic medic;

    @NotNull(message = "Appointment day is required")
    @Future(message = "Appointment day must be in the future")
    private Date appointmentDay;

    @NotNull(message = "Appointment time is required")
    private String appointmentTime;

    @NotNull(message = "Status is required")
    private String status;

    public Appointment() {
    }

    public Appointment(Patient patient, String specialty, Medic medic, Date appointmentDate, String appointmentTime, String status) {
        this.patient = patient;
        this.specialty = specialty;
        this.medic = medic;
        this.appointmentDay = appointmentDate;
        this.appointmentTime = appointmentTime;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Date getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(Date appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}