package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.AppointmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.AppointmentResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/appointments")
@AllArgsConstructor
@Tag(
        name = "Appointments",
        description = "Manage patient appointments"
)
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Operation(summary = "Find an appointment", description = "Find all  appointments assigned to doctors and patients ")
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
    @Operation(summary = "Create an appointment", description = "Create an appointment ")
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> addAppointment(
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.addAppointment(requestDTO));
    }
    @Operation(summary = "Delete an appointment", description = "Deletes an appointment only if no prescription exists for it")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(
                Map.of("message", "Appointment deleted successfully")
        );
    }
    @Operation(summary = " Update an appointment status", description = "you can update status scheduled|completed|canceled")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Map<String, String>> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        AppointmentResponseDTO updated =
                appointmentService.updateStatus(id, status);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Appointment status updated successfully",
                        "appointmentId", updated.getAppointmentId().toString(),
                        "status", updated.getStatus()
                )
        );
    }
}

