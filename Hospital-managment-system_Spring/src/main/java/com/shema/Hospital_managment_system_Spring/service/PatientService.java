package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Patient;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.PatientDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.PatientRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PatientResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PatientService {

    private final PatientDao patientDAO;

    // GET BY ID
    public PatientResponseDTO getPatientById(long patientId) {
        Patient patient = patientDAO.searchPatientById(patientId);

        if (patient == null) {
            throw new NotFoundException("Patient not found with ID: " + patientId);
        }

        return mapToResponseDTO(patient);
    }

    // GET ALL
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        if (patients.isEmpty()) {
            throw new NotFoundException("No patients found");
        }
        return patients.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    // ADD
    public PatientResponseDTO addPatient(PatientRequestDTO dto) {
        if (dto == null) {
            throw new BadRequestException("Patient data is required");
        }
        if (contactExist(dto.getContactNumber(), 0l)){
            throw new BadRequestException("Contact number already exist");
        }
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDob(dto.getDob());
        patient.setGender(dto.getGender());
        patient.setContactNumber(dto.getContactNumber());
        patient.setAddress(dto.getAddress());

        patientDAO.addPatient(patient);

        return mapToResponseDTO(patient);
    }

    // UPDATE
    public void updatePatient(long id, PatientRequestDTO dto) {

        Patient existing = patientDAO.searchPatientById(id);
        if (existing == null) {
            throw new NotFoundException("Patient not found with ID: " + id);
        }
        if (contactExist(dto.getContactNumber(), id)){
            throw new BadRequestException("Contact number already exist");
        }
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setDob(dto.getDob());
        existing.setGender(dto.getGender());
        existing.setContactNumber(dto.getContactNumber());
        existing.setAddress(dto.getAddress());

        patientDAO.updatePatient(existing);
    }

    // DELETE
    public void deletePatient(long id) {
        Patient existing = patientDAO.searchPatientById(id);
        if (existing == null) {
            throw new NotFoundException("Patient not found with ID: " + id);
        }
        patientDAO.deletePatient(id);
    }

    // SEARCH
    public List<PatientResponseDTO> searchPatientByLastName(String lastName) {
        List<Patient> patients = patientDAO.searchPatientByLastName(lastName);

        if (patients.isEmpty()) {
            throw new NotFoundException("No patients found with last name: " + lastName);
        }
        return patients.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    public boolean contactExist(String contact,Long excludePatientId){
        return patientDAO.contactExistsInPatients(contact,excludePatientId);
    }

    // DTO MAPPER
    private PatientResponseDTO mapToResponseDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDob(),
                patient.getGender(),
                patient.getContactNumber(),
                patient.getAddress()
        );
    }
}
