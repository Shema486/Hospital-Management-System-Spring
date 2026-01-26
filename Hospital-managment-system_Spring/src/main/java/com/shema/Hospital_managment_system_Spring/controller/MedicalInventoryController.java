package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.MedicalInventoryRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.MedicalInventoryResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.MedicalInventoryService;
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
public class MedicalInventoryController {
    private MedicalInventoryService medicalInventoryService;

    @GetMapping
    public ResponseEntity<List<MedicalInventoryResponseDTO>>getAllInventory(){
        return ResponseEntity.ok(medicalInventoryService.getAllInventoryItems());
    }
    @PostMapping
    public ResponseEntity<MedicalInventoryResponseDTO>addInventory(
            @Valid @RequestBody MedicalInventoryRequestDTO requestDTO
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalInventoryService.addInventoryItem(requestDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>>update(
            @PathVariable Long id,
            @Valid @RequestBody MedicalInventoryRequestDTO requestDTO
    ){
        medicalInventoryService.updateInventoryItem(id,requestDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","Inventory updated successfully");

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteInventory(@PathVariable Long id){
        medicalInventoryService.deleteInventoryItem(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Inventory deleted successfully");
        return ResponseEntity.ok(response);
    }
}
