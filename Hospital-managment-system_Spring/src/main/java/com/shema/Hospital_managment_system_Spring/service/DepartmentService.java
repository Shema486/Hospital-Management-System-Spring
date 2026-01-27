package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Department;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.DepartmentDao;
import com.shema.Hospital_managment_system_Spring.repository.DoctorDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.DepartmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DepartmentResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentDao departmentDao;
    private final DoctorDao doctorDao;

    // ADD
    public DepartmentResponseDTO addDepartment(DepartmentRequestDTO dto) {
        if (dto == null || dto.getDeptName() == null) {
            throw new BadRequestException("Department name is required");
        }

        Department department = new Department();
        department.setDeptName(dto.getDeptName());
        department.setLocationFloor(dto.getLocationFloor());

        departmentDao.addDepartment(department);

        return mapToResponseDTO(department);
    }

    // GET BY ID
    public DepartmentResponseDTO findByID(Long id) {
        Department department = departmentDao.findById(id);
        if (department == null) {
            throw new NotFoundException("Department not found with id: " + id);
        }
        return mapToResponseDTO(department);
    }

    // GET ALL
    public List<DepartmentResponseDTO> getAllDepartment() {
        List<Department> departments = departmentDao.getAllDepartment();
        if (departments.isEmpty()) {
            throw new NotFoundException("No departments found");
        }
        return departments.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // UPDATE
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {
        Department existing = departmentDao.findById(id);
        if (existing == null) {
            throw new NotFoundException("Department not found with id: " + id);
        }
        existing.setDeptName(dto.getDeptName());
        existing.setLocationFloor(dto.getLocationFloor());
        departmentDao.updateDepartment(existing);
        return mapToResponseDTO(existing);
    }

    // DELETE
    public void deleteDepartment(Long id) {
        Department department = departmentDao.findById(id);
        if (department == null) {
            throw new NotFoundException("Department not found with id: " + id);
        }

        int doctorCount = doctorDao.countByDepartmentId(id);
        if (doctorCount > 0) {
            throw new BadRequestException(
                    "Cannot delete department: it has " + doctorCount + " doctor(s) assigned"
            );
        }
        departmentDao.deleteDepartment(id);
    }

    // MAPPER
    private DepartmentResponseDTO mapToResponseDTO(Department department) {
        return new DepartmentResponseDTO(
                department.getDeptId(),
                department.getLocationFloor(),
                department.getDeptName()
        );
    }
}
