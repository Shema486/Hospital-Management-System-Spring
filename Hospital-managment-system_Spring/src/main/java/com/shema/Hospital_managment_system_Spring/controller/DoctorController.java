package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.DoctorRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DoctorResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
@AllArgsConstructor
@Tag(name = "Doctors", description = "Manage doctors and their department assignments")
public class DoctorController {

    private DoctorService doctorService;
    @Operation(
            summary = "Find all Doctors",
            description = "You can find all doctors in all departments"
    )
    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctor(
            @Parameter(description = "Page number (>= 1)", example = "1")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "Page size (1–100)", example = "10")
            @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(doctorService.getDoctors(page, size));
        }
        return ResponseEntity.ok(doctorService.getAllDoctor());
    }
    @Operation(
            summary = "Get a doctor By ID",
            description = "You can find a doctor by his/her Id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
    @Operation(
            summary = "Search a doctor",
            description = "Search doctors according to their specialization"
    )
    @GetMapping("/specialization")
    public ResponseEntity<List<DoctorResponseDTO>> findBySpecialization(
            @RequestParam String specialization) {
        return ResponseEntity.ok(
                doctorService.findDoctorsBySpecialization(specialization)
        );
    }
    @Operation(summary = "Create a doctor", description = "Registers a doctor and assigns them to a department")
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addDoctor(
            @Valid @RequestBody DoctorRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.addDoctor(requestDTO));
    }
    @Operation(
            summary = "Update a doctor",
            description = "you can update  names, specialization, email, phone and also department"
    )
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, dto));
    }
    @Operation(
            summary = "Delete a doctor",
            description = "use Id of doctor to delete him/her"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(Map.of("message", "Doctor deleted successfully"));
    }
}
