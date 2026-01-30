package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.PatientRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PatientResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
@Tag(name = "Patient Management", description = "APIs for managing patients")
public class PatientController {

    private final PatientService patientService;

    @Operation(
            summary = "Get all patients",
            description = "Returns a list of all registered patients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "404", description = "No patients found")
            }
    )
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll(
            @Parameter(description = "Page number (>= 1)", example = "1")
            @RequestParam(required = false) Integer page,
            @Parameter(description = "Page size (1–100)", example = "10")
            @RequestParam(required = false) Integer size
    ) {
        if (page !=null && size !=null){
            return ResponseEntity.ok(patientService.getPatients(page,size));
        }
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(
            summary = "Get  patient By Id",
            description = "Returns a  patient",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved "),
                    @ApiResponse(responseCode = "404", description = "No patients found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
    @Operation(
            summary = "search  patients by lastName",
            description = "Returns a list of  registered patients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "404", description = "No patients found")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<PatientResponseDTO>> search(
            @RequestParam String lastName) {
        return ResponseEntity.ok(patientService.searchPatientByLastName(lastName));
    }

    @Operation(
            summary = "Add a new patient",
            description = "Creates a new patient record",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Patient created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping
    public ResponseEntity<PatientResponseDTO> add(
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(dto));
    }

    @Operation(
            summary = "Update a  patient",
            description = "return the patient that updated",
            responses = {
                    @ApiResponse(responseCode = "201", description = "update successfully"),
                    @ApiResponse(responseCode = "404", description = "No patient found")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.updatePatient(id, dto));
    }

    @Operation(
            summary = "Delete patient",
            description = "remove  patient record",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Patient deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "no patient found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
        patientService.deletePatient(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient deleted successfully");
        return ResponseEntity.ok(response);
    }
}
