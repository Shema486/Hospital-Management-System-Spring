package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.entity.PrescriptionItem;
import com.shema.Hospital_managment_system_Spring.service.PrescriptionItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prescription-items")
@AllArgsConstructor
@Tag(name = "Prescription Items Management", description = "APIs for managing prescription items")
public class PrescriptionItemController {

    private final PrescriptionItemService service;


    @Operation(
            summary = "Add a prescription item",
            description = "Creates a new item for a prescription",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prescription item added successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> addItem(
            @RequestBody PrescriptionItem item) {

        service.addItem(item);
        return ResponseEntity.ok(
                Map.of("message", "Prescription item added successfully")
        );
    }

    @Operation(
            summary = "Get all prescription items",
            description = "Retrieve all items associated with prescriptions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved items"),
                    @ApiResponse(responseCode = "404", description = "No prescription items found")
            }
    )
    @GetMapping
    public ResponseEntity<List<PrescriptionItem>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }
}
