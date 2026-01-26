package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.PatientRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PatientResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PatientService;
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
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientResponseDTO>> search(
            @RequestParam String lastName) {
        return ResponseEntity.ok(patientService.searchPatientByLastName(lastName));
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> add(
            @Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(
            @PathVariable long id,
            @Valid @RequestBody PatientRequestDTO dto) {
        patientService.updatePatient(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable long id) {
        patientService.deletePatient(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Patient deleted successfully");
        return ResponseEntity.ok(response);
    }
}
