package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.entity.PrescriptionItem;
import com.shema.Hospital_managment_system_Spring.service.PrescriptionItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prescription-items")
@AllArgsConstructor
public class PrescriptionItemController {

    private final PrescriptionItemService service;


    @PostMapping
    public ResponseEntity<Map<String, String>> addItem(
            @RequestBody PrescriptionItem item) {

        service.addItem(item);
        return ResponseEntity.ok(
                Map.of("message", "Prescription item added successfully")
        );
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionItem>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }
}
