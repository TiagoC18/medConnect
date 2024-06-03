package project.medconnect;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.medconnect.entity.Appointment;
import project.medconnect.entity.Medic;
import project.medconnect.entity.Patient;
import project.medconnect.repository.AppointmentRepository;
import project.medconnect.repository.MedicRepository;
import project.medconnect.repository.PatientRepository;
import project.medconnect.repository.StaffRepository;
import project.medconnect.entity.Staff;

@Deprecated
@Component
public class DataInitializer implements CommandLineRunner {

        private final MedicRepository medicRepository;
        private final PatientRepository patientRepository;
        private final AppointmentRepository appointmentRepository;
        private final StaffRepository staffRepository;

        @Autowired
        public DataInitializer(MedicRepository medicRepository, PatientRepository patientRepository,
                        AppointmentRepository appointmentRepository, StaffRepository staffRepository) {
                this.medicRepository = medicRepository;
                this.patientRepository = patientRepository;
                this.appointmentRepository = appointmentRepository;
                this.staffRepository = staffRepository;
        }

        @Override
        public void run(String... args) {
                // Criação de médicos
                final String cardiology = "Cardiology";
                final String dermatology = "Dermatology";
                final String neurology = "Neurology";
                final String pediatrics = "Pediatrics";
                final String orthopedics = "Orthopedics";
                final String gynecology = "Gynecology";
                final String masculino = "Masculino";
                final String feminino = "Feminino";
                final String scheduled = "Scheduled";
                final String waiting = "Waiting";
                final String called = "Called";
                final String done = "Done";
                final String june03 = "2024-06-03";
                final String june04 = "2024-06-04";
                final String june05 = "2024-06-05";

                Medic medic1 = new Medic("Dr. John", "Doe", "johndoe@hospital.com", "123456789", cardiology,
                                Arrays.asList("9h", "10h", "11h", "14h", "15h"));
                Medic medic2 = new Medic("Dr. Jane", "Smith", "janesmith@hospital.com", "987654321", pediatrics,
                                Arrays.asList("10h", "11h", "13h", "15h", "17h"));
                Medic medic3 = new Medic("Dr. Emily", "Clark", "emilyclark@hospital.com", "123123123", neurology,
                                Arrays.asList("8h", "9h", "10h", "14h", "16h"));
                Medic medic4 = new Medic("Dr. William", "Brown", "williambrown@hospital.com", "321321321", dermatology,
                                Arrays.asList("9h", "11h", "12h", "14h", "16h"));
                Medic medic5 = new Medic("Dr. Michael", "Johnson", "michaeljohnson@hospital.com", "456456456",
                                orthopedics,
                                Arrays.asList("10h", "12h", "14h", "15h", "17h"));
                Medic medic6 = new Medic("Dr. Sarah", "Davis", "sarahdavis@hospital.com", "654654654", gynecology,
                                Arrays.asList("9h", "11h", "13h", "15h", "17h"));
                Medic medic7 = new Medic("Dr. Anna", "Lee", "annalee@hospital.com", "789789789", cardiology,
                                Arrays.asList("8h", "10h", "12h", "14h", "16h"));
                Medic medic8 = new Medic("Dr. David", "White", "davidwhite@hospital.com", "987987987", dermatology,
                                Arrays.asList("8h", "10h", "12h", "14h", "16h"));
                Medic medic9 = new Medic("Dr. Laura", "Martinez", "lauramartinez@hospital.com", "456123789", neurology,
                                Arrays.asList("9h", "11h", "13h", "15h", "17h"));

                medicRepository.saveAll(
                                Arrays.asList(medic1, medic2, medic3, medic4, medic5, medic6, medic7, medic8, medic9));

                // Criação de pacientes
                Patient patient1 = new Patient("Alice", "Johnson", new Date(90, 5, 15), feminino, "123456789",
                                "987654321", "alice@example.com", "senha123");
                Patient patient2 = new Patient("Bob", "Smith", new Date(85, 8, 25), masculino, "234567890", "876543219",
                                "bob@example.com", "senha456");
                Patient patient3 = new Patient("Charlie", "Brown", new Date(82, 10, 8), masculino, "345678901",
                                "765432198", "charlie@example.com", "senha789");
                Patient patient4 = new Patient("Diana", "Lee", new Date(78, 4, 20), feminino, "456789012", "654321987",
                                "diana@example.com", "senhaabc");
                Patient patient5 = new Patient("Eva", "Garcia", new Date(95, 12, 10), feminino, "567890123",
                                "543219876", "eva@example.com", "senhadef");
                Patient patient6 = new Patient("Frank", "Miller", new Date(89, 2, 3), masculino, "678901234",
                                "432198765", "frank@example.com", "senhaghi");
                Patient patient7 = new Patient("Grace", "Wilson", new Date(88, 3, 14), feminino, "789012345",
                                "321987654", "grace@example.com", "senhajkl");
                Patient patient8 = new Patient("Hank", "Moore", new Date(91, 6, 22), masculino, "890123456",
                                "210876543", "hank@example.com", "senhamno");
                Patient patient9 = new Patient("Ivy", "Taylor", new Date(87, 11, 5), feminino, "901234567",
                                "109765432", "ivy@example.com", "senhapqr");
                Patient patient10 = new Patient("Jack", "Anderson", new Date(86, 1, 30), masculino, "012345678",
                                "098654321", "jack@example.com", "senhastu");
                Patient patient11 = new Patient("Kara", "Thomas", new Date(94, 7, 19), feminino, "123789456",
                                "987321654", "kara@example.com", "senha789");
                Patient patient12 = new Patient("Leo", "Jackson", new Date(83, 9, 24), masculino, "234891567",
                                "876210543", "leo@example.com", "senhaabc");
                Patient patient13 = new Patient("Mia", "Harris", new Date(92, 10, 13), feminino, "345912678",
                                "765109432", "mia@example.com", "senhadef");
                Patient patient14 = new Patient("Nick", "Martinez", new Date(79, 8, 11), masculino, "456123789",
                                "654098321", "nick@example.com", "senhaghi");
                Patient patient15 = new Patient("Olivia", "Clark", new Date(81, 2, 29), feminino, "567234890",
                                "543987210", "olivia@example.com", "senhajkl");

                patientRepository.saveAll(Arrays.asList(patient1, patient2, patient3, patient4, patient5, patient6,
                                patient7, patient8, patient9, patient10, patient11, patient12, patient13, patient14,
                                patient15));

                // Criação de consultas
                Appointment appointment1 = new Appointment(patient1, cardiology, medic1, "2024-06-02", "10h", done,
                                null);
                Appointment appointment2 = new Appointment(patient2, pediatrics, medic2, june03, "10h", done,
                                null);
                Appointment appointment3 = new Appointment(patient3, cardiology, medic1, june03, "11h", called,
                                1);
                Appointment appointment4 = new Appointment(patient4, pediatrics, medic2, june03, "11h", called,
                                2);
                Appointment appointment5 = new Appointment(patient5, cardiology, medic1, june03, "12h", waiting,
                                3);
                Appointment appointment6 = new Appointment(patient6, pediatrics, medic2, june03, "12h", waiting,
                                4);
                Appointment appointment7 = new Appointment(patient7, neurology, medic3, june04, "10h", scheduled,
                                null);
                Appointment appointment8 = new Appointment(patient8, dermatology, medic4, june04, "11h",
                                scheduled, null);
                Appointment appointment9 = new Appointment(patient9, orthopedics, medic5, june04, "12h",
                                scheduled, null);
                Appointment appointment10 = new Appointment(patient10, gynecology, medic6, june05, "10h",
                                scheduled, null);
                Appointment appointment11 = new Appointment(patient11, cardiology, medic7, june05, "11h",
                                scheduled, null);
                Appointment appointment12 = new Appointment(patient12, dermatology, medic8, june05, "12h",
                                scheduled, null);
                Appointment appointment13 = new Appointment(patient13, neurology, medic9, june05, "12h",
                                scheduled, null);
                Appointment appointment14 = new Appointment(patient14, orthopedics, medic5, june05, "12h",
                                scheduled, null);
                Appointment appointment15 = new Appointment(patient15, gynecology, medic6, june05, "12h",
                                scheduled, null);

                appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4,
                                appointment5, appointment6, appointment7, appointment8, appointment9, appointment10,
                                appointment11, appointment12, appointment13, appointment14, appointment15));

                // Criação de staff
                Staff staff1 = new Staff("Staff1", "Teste", "staff1@ua.pt", "staff1");
                Staff staff2 = new Staff("Staff2", "Teste", "staff2@ua.pt", "staff2");

                staffRepository.saveAll(Arrays.asList(staff1, staff2));
        }
}
