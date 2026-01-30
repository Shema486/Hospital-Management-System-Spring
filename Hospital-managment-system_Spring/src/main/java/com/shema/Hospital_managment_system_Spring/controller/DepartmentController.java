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
@Tag(name = "Departments", description = "Manage hospital departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(
            summary = "Find all departments",
            description = "Get all departments that exist"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Departments not found")})
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    // GET BY ID
    @Operation(
            summary = "Find a department ",
            description = "Find a department By ID assigned to it"
    )
    @ApiResponses({@ApiResponse(responseCode = "404", description = "Department not found")})
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.findByID(id));
    }

    // ADD
    @Operation(summary = "Create a department", description = "Create new  department ")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Department added successfully"),})
    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> addDepartment(
            @Valid @RequestBody DepartmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(dto));
    }

    // UPDATE
    @Operation(
            summary = "Update a department",
            description = "Update a department with ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO dto) {
        return departmentService.updateDepartment(id, dto);
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
