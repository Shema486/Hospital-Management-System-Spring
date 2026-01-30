package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.MedicalInventoryRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.MedicalInventoryResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.MedicalInventoryService;
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
@AllArgsConstructor
@RequestMapping("/api/inventory")
@Tag(
        name = "Medical Inventory",
        description = "APIs for managing medical inventory items such as medicines and supplies"
)
public class MedicalInventoryController {
    private MedicalInventoryService medicalInventoryService;

    @Operation(
            summary = "Get all inventory items",
            description = "Retrieve a list of all medical inventory items available in the hospital"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventory items retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No inventory items found")
    })
    @GetMapping
    public ResponseEntity<List<MedicalInventoryResponseDTO>>getAllInventory(){
        return ResponseEntity.ok(medicalInventoryService.getAllInventoryItems());
    }
    @Operation(
            summary = "Add new inventory item",
            description = "Create a new medical inventory item (medicine or supply)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Inventory item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid inventory data")
    })

    @PostMapping
    public ResponseEntity<MedicalInventoryResponseDTO>addInventory(
            @Valid @RequestBody MedicalInventoryRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalInventoryService.addInventoryItem(requestDTO));
    }
    @Operation(
            summary = "Update inventory item",
            description = "Update an existing medical inventory item by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventory item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Inventory item not found"),
            @ApiResponse(responseCode = "400", description = "Invalid update data")
    })

    @PutMapping("/{id}")
    public ResponseEntity<MedicalInventoryResponseDTO>update(
            @PathVariable Long id,
            @Valid @RequestBody MedicalInventoryRequestDTO requestDTO
    ){
        return ResponseEntity.ok(medicalInventoryService.updateInventoryItem(id,requestDTO));
    }
    @Operation(
            summary = "Delete inventory item",
            description = "Delete a medical inventory item by ID. Deletion may be blocked if the item is used in prescriptions."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventory item deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Item is used in prescriptions"),
            @ApiResponse(responseCode = "404", description = "Inventory item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteInventory(@PathVariable Long id){
        medicalInventoryService.deleteInventoryItem(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Inventory deleted successfully");
        return ResponseEntity.ok(response);
    }
}
