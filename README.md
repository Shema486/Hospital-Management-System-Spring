# Hospital Management System

A comprehensive Hospital Management System built with Spring Boot and GraphQL for managing patients, doctors, appointments, departments, medical inventory, prescriptions, and feedback.

##  Features
- **Patient Management** - Add, update, delete, and retrieve patient information
- **Doctor Management** - Manage doctor profiles and specializations
- **Appointments** - Schedule and manage patient appointments
- **Departments** - Organize and manage hospital departments
- **Prescriptions** - Create and manage prescription records with items
- **Medical Inventory** - Track medical supplies and equipment
- **Feedback System** - Collect and manage patient feedback
- **Reports** - Generate hospital reports and analytics
- **Logging & Monitoring** - AOP-based logging and performance monitoring
- **GraphQL API** - Flexible GraphQL queries and mutations for seamless integration
##  Technology Stack
- **Java 21**
- **Spring Boot 4**
- **Spring GraphQL**
- **Spring OpenApi**
- **Lombok**
- **POSTGRESQL**
- **Maven**
- **AOP**
##  Project Structure
```
src/main/java/com/shema/Hospital_managment_system_Spring/hospital-management-system/
├─ src/
│  ├─ main/
│  │  ├─ java/com/shema/Hospital_managment_system_Spring/
│  │  │  ├─ aspect/                  # LoggingAspect (AOP for performance and exceptions)
│  │  │  ├─ controller/               # REST Controllers (optional)
│  │  │  ├─ graphqlController/        # GraphQL controllers for queries and mutations
│  │  │  ├─ repository/               # Repositories for DB access
│  │  │  │  └─ dto/                   # DTOs for requests and responses
│  │  │  ├─ service/                  # Business logic services
│  │  │  ├─ entity/                   # JPA entities (Patient, Appointment, Department, etc.)
│  │  │  ├─ HospitalManagmentSystemSpringApplication.java
│  │  └─ resources/
│  │     ├─ graphql/                  # GraphQL schema files (*.graphqls)
│  │     ├─ application.properties    # Spring Boot configuration
│  │     └─ data.sql / schema.sql     # Optional DB initialization scripts
├─ pom.xml                             # Maven dependencies
└─ README.md

```
##  Installation
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd Hospital-managment-system_Spring
   ```
2. **Build the project:**
   ```bash
   mvn clean install
   ```
##  Running the Application
**Using Maven:**
```bash
mvn spring-boot:run
```
**Using Java:**
```bash
java -jar target/hospital-managment-system-1.0.0.jar
```
The application will start on `http://localhost:8080`
##  API Documentation
###  Swagger UI (REST API Documentation)
Once the application is running, access the interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
```
**Swagger provides:**
- Interactive API explorer
- Real-time request/response testing
- Complete endpoint documentation
- Request/response schema visualization
- Authentication/Authorization setup
###  GraphQL Endpoint
```
POST http://localhost:8080/graphql
```
**GraphQL IDE:**
```
http://localhost:8080/graphiql
```
### Available GraphQL Schemas
- **Patient** - Patient management queries and mutations
- **Doctor** - Doctor management queries and mutations
- **Appointment** - Appointment scheduling and management
- **Department** - Department operations
- **Prescription** - Prescription management
- **Inventory** - Medical inventory tracking
- **SystemUser** - User management
###  REST API Endpoints
#### Patient Endpoints
```
GET    /api/patients              # Get all patients
POST   /api/patients              # Create new patient
GET    /api/patients/{id}         # Get patient by ID
PUT    /api/patients/{id}         # Update patient
DELETE /api/patients/{id}         # Delete patient
```
#### Doctor Endpoints
```
GET    /api/doctors               # Get all doctors
POST   /api/doctors               # Create new doctor
GET    /api/doctors/{id}          # Get doctor by ID
PUT    /api/doctors/{id}          # Update doctor
DELETE /api/doctors/{id}          # Delete doctor
```
#### Appointment Endpoints
```
GET    /api/appointments          # Get all appointments
POST   /api/appointments          # Schedule appointment
GET    /api/appointments/{id}     # Get appointment by ID
PUT    /api/appointments/{id}     # Update appointment
DELETE /api/appointments/{id}     # Cancel appointment
```
#### Department Endpoints
```
GET    /api/departments           # Get all departments
POST   /api/departments           # Create department
GET    /api/departments/{id}      # Get department by ID
PUT    /api/departments/{id}      # Update department
DELETE /api/departments/{id}      # Delete department
```
#### Prescription Endpoints
```
GET    /api/prescriptions         # Get all prescriptions
POST   /api/prescriptions         # Create prescription
GET    /api/prescriptions/{id}    # Get prescription by ID
PUT    /api/prescriptions/{id}    # Update prescription
DELETE /api/prescriptions/{id}    # Delete prescription
```
#### Medical Inventory Endpoints
```
GET    /api/inventory             # Get all inventory items
POST   /api/inventory             # Add inventory item
GET    /api/inventory/{id}        # Get inventory by ID
PUT    /api/inventory/{id}        # Update inventory
DELETE /api/inventory/{id}        # Delete inventory
```
#### Feedback Endpoints
```
GET    /api/feedback              # Get all feedback
POST   /api/feedback              # Submit feedback
GET    /api/feedback/{id}         # Get feedback by ID
DELETE /api/feedback/{id}         # Delete feedback
```
#### Report Endpoints
```
GET    /api/reports               # Get all reports
POST   /api/reports/generate      # Generate new report
GET    /api/reports/{id}          # Get report by ID
```
##  Logging & Monitoring
The application uses AOP (Aspect-Oriented Programming) for:
- **LoggingAspect** - Logs method execution with entry/exit points and exceptions
- **PerformanceAspect** - Tracks method execution time for performance analysis
##  License
This project is licensed under the MIT License - see the LICENSE file for details.
##  Contributing
Contributions are welcome! Please follow the standard GitHub workflow (fork, branch, commit, push, pull request).
