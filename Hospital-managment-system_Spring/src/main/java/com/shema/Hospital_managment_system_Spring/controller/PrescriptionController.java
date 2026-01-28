package com.shema.Hospital_managment_system_Spring.controller;


import com.shema.Hospital_managment_system_Spring.repository.dto.request.PrescriptionRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PrescriptionResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/prescriptions")
@AllArgsConstructor
@Tag(name = "Prescriptions Management", description = "APIs for managing prescriptions")
public class PrescriptionController {
    private PrescriptionService prescriptionService;

    @Operation(
            summary = "Get all prescriptions",
            description = "Retrieve all prescriptions issued to patients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved prescriptions"),
                    @ApiResponse(responseCode = "404", description = "No prescriptions found")
            }
    )
    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDTO>> findAll(){
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }
    @Operation(
            summary = "Delete a prescription",
            description = "remove a  prescription record",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prescription removed successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deletePrescription(@PathVariable Long id){
        prescriptionService.deletePrescription(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","prescription deleted successfully");
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Add a prescription",
            description = "Creates a new prescription record",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prescription added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping
    public ResponseEntity<PrescriptionResponseDTO>addPrescription(@Valid @RequestBody PrescriptionRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionService.addPrescription(requestDTO));
    }
    @Operation(
            summary = "Update a prescription",
            description = "update prescription notes",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prescription notes successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PatchMapping("/{id}/notes")
    public ResponseEntity<Map<String, String>> updateNotes(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String notes = body.get("notes");
        prescriptionService.updatePrescriptionNotes(id, notes);

        return ResponseEntity.ok(
                Map.of("message", "Prescription notes updated successfully")
        );
    }


}
