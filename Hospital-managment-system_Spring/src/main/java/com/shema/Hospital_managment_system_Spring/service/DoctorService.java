package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Department;
import com.shema.Hospital_managment_system_Spring.entity.Doctor;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.DepartmentDao;
import com.shema.Hospital_managment_system_Spring.repository.DoctorDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.DoctorRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DepartmentResponseDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DoctorResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class DoctorService {

    private final DoctorDao doctorDao;
    private final DepartmentDao departmentDao;



    public DoctorResponseDTO addDoctor(DoctorRequestDTO dto) {
        if (dto == null) {
            throw new BadRequestException("Doctor data is required");
        }

        if (isEmailUnique(dto.getEmail(), 0)) {
            throw new BadRequestException("Email already exists");
        }
        if (contactNumberUnique(dto.getPhone(), 0)) {
            throw new BadRequestException("Phone number already exists");
        }
        if (!departmentDao.existByID(dto.getDepartment())) {
            throw new BadRequestException("Invalid department ID");
        }

        Doctor doctor = new Doctor();
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDepartment(dto.getDepartment());

        doctorDao.addDoctor(doctor);
        return mapToResponse(doctor);
    }

    public List<DoctorResponseDTO> getAllDoctor() {
        List<Doctor> doctors = doctorDao.getAllDoctors();
        if (doctors.isEmpty()) {
            throw new NotFoundException("No doctors found");
        }
        return doctors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DoctorResponseDTO getDoctorById(Long doctorId) {
        Doctor doctor = doctorDao.searchDoctorById(doctorId);
        if (doctor == null) {
            throw new NotFoundException("Doctor not found with ID: " + doctorId);
        }
        return mapToResponse(doctor);
    }

    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO dto) {
        if (dto == null) {
            throw new BadRequestException("Doctor data is required");
        }

        Doctor existing = doctorDao.searchDoctorById(id);
        if (existing == null) {
            throw new NotFoundException("Doctor not found with ID: " + id);
        }

        if (isEmailUnique(dto.getEmail(), id)) {
            throw new BadRequestException("Email already exists");
        }
        if (contactNumberUnique(dto.getPhone(), id)) {
            throw new BadRequestException("Phone number already exists");
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setSpecialization(dto.getSpecialization());
        existing.setDepartment(dto.getDepartment());

        doctorDao.updateDoctor(existing);
        return mapToResponse(existing);
    }

    public void deleteDoctor(Long doctorId) {
        Doctor existing = doctorDao.searchDoctorById(doctorId);
        if (existing == null) {
            throw new NotFoundException("Doctor not found with ID: " + doctorId);
        }
        doctorDao.deleteDoctor(doctorId);
    }

    public List<DoctorResponseDTO> findDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = doctorDao.findDoctorsBySpecialization(specialization);
        if (doctors.isEmpty()) {
            throw new NotFoundException("No doctors found with specialization: " + specialization);
        }
        return doctors.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public boolean isEmailUnique(String email, long excludeDoctorId) {
        return doctorDao.emailExists(email, excludeDoctorId);
    }
    public boolean contactNumberUnique(String contactNumber, long excludeDoctorId) {
        return doctorDao.contactExistsInDoctors(contactNumber, excludeDoctorId);
    }
    public DoctorResponseDTO mapToResponse(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getDoctorId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecialization(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDepartment()
        );
    }



}
