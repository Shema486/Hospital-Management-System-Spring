package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.DepartmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DepartmentResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/department")
@AllArgsConstructor
@Tag(
        name = "Departments",
        description = "Manage hospital departments"
)
public class DepartmentController {

    private final DepartmentService departmentService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findByID(id));
    }

    // ADD
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> addDepartment(
            @Valid @RequestBody DepartmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(dto));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO dto) {
        departmentService.updateDepartment(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Department updated successfully");

        return ResponseEntity.ok(response);
    }

    // DELETE
    @Operation(
            summary = "Delete a department",
            description = "Deletes a department only if it does not contain any doctors"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Department has assigned doctors"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Department deleted successfully with id: " + id);
        return ResponseEntity.ok(response);
    }
}
