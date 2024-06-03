# medConnect

## Project Abstract
**MedConnect** is an integrated IT solution designed to streamline patient appointments and admissions for the New Private Health Initiative (newPhi). This project, developed as part of the Total Quality Software course, focuses on enhancing the efficiency and accessibility of healthcare services through digital solutions that manage pre-appointment scheduling, reception services, and post-encounter processes.



| Name | NMec | Email | Role |
|------|------|------|------|
| Gonçalo Lopes | 107572 | goncalorcml@ua.pt | QA Engineer |
| Rodrigo Graça | 107634 | rodrigomgraca@ua.pt | DevOps Master |
| Tiago Cruz | 108615 | tiagofcruz78@ua.pt | Team Coordinator |


## Project Bookmarks

### Project Management Tools
- [Project Backlog in JIRA](https://tiagofcruz78.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog) - Agile planning and backlog management.
- [Related Repositories on GitHub](https://github.com/TiagoC18/newPhi) - Access to all project code repository.

### Collaboration Tools
- [Shared OneDrive Workspace](https://uapt33090-my.sharepoint.com/:f:/g/personal/tiagofcruz78_ua_pt/EnM4OJ3Awo1MlxC3y3Ngl4gBxEy2cs4P3P8LqFD5uM0VMQ?e=RpWfc4) - For all project-related documents and files.

### Continuous Integration and Deployment
- [Code Dashboard](https://sonarcloud.io/project/overview?id=TiagoC18_medConnect) - View the latest builds and deployment statuses.


### Documentation and Reports
- [API Documentation](http://localhost:8080/swagger-ui/index.html) - Comprehensive guide and reference to the project's APIs.

#### Appointment Management
- **Create, Update, and Delete Appointments**: Developers can create new appointments, update the status of existing ones, and delete appointments using endpoints such as `POST /api/appointment`, `PUT /api/appointment/{appointmentId}/{newStatus}`, and `DELETE /api/appointment/delete/{appointmentId}`.
- **Retrieve Appointment Information**: The API allows retrieval of all appointments, specific appointments by patient ID, and appointments based on status (waiting, scheduled, done, called) using endpoints like `GET /api/appointment`, `GET /api/appointment/patient/{patientId}`, `GET /api/appointment/waiting`, etc.
- **Booked Appointments**: Developers can fetch booked appointments for a specific specialty and date using `GET /api/appointment/booked/{specialty}/{firstName}/{lastName}/{date}`.


### Patient Management
- **Patient Information Retrieval**: Endpoints such as `GET /api/patient`, `GET /api/patient/{patientId}`, and `GET /api/patient/byEmail/{email}` provide detailed information about patients.
- **Patient Authentication**: The `POST /api/patient/checkPassword` endpoint is used to verify patient credentials.


### Staff Operations
- **Staff Information**: Retrieve information about staff members with endpoints like `GET /api/staff` and `GET /api/staff/{staffId}`.
- **Staff Authentication**: Verify staff credentials using `POST /api/staff/checkPassword`.



### Medic Information
- **Medic Details and Specialties**: Fetch detailed information about medics and their specialties using endpoints such as `GET /api/medic`, `GET /api/medic/{medicId}`, `GET /api/medic/specialty/{specialty}`, and `GET /api/medic/name/{firstName}/{lastName}`.
- **Service Time**: Retrieve the service time for specific medics using `GET /api/medic/{medicId}/serviceTime`.




-------

### (future implementation)
### Documentation and Reports
- [API Documentation](https://your-api-documentation-link.com) - Comprehensive guide and reference to the project's APIs (**Coming Soon**).
- [Static Analysis Dashboard](https://your-sonarqube-dashboard-link.com) - Quality dashboard for code analysis results (**To Be Implemented**).

Please refer to these links for detailed information and resources related to the **medConnect** project. For any additional inquiries or support, please contact the project team members.
