package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.entity.Prescription;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.PrescriptionRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PrescriptionResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PrescriptionService;
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
public class PrescriptionController {
    private PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDTO>> findAll(){
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deletePrescription(@PathVariable Long id){
        prescriptionService.deletePrescription(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","prescription deleted successfully");
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<PrescriptionResponseDTO>addPrescription(@Valid @RequestBody PrescriptionRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionService.addPrescription(requestDTO));
    }
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
