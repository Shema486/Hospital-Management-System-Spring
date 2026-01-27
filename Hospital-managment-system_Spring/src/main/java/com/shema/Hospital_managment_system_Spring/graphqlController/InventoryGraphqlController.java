package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.MedicalInventoryRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.MedicalInventoryResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.MedicalInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class InventoryGraphqlController {
    private MedicalInventoryService medicalInventoryService;

    @QueryMapping
    public List<MedicalInventoryResponseDTO> inventories(){
        return medicalInventoryService.getAllInventoryItems();
    }
    @MutationMapping
    public MedicalInventoryResponseDTO addInventory(
            @Valid @Argument MedicalInventoryRequestDTO requestDTO){
        return medicalInventoryService.addInventoryItem(requestDTO);
    }
    @MutationMapping
    public MedicalInventoryResponseDTO updateInventory(
            @Argument Long id,
            @Valid @Argument("input") MedicalInventoryRequestDTO requestDTO){
        return medicalInventoryService.updateInventoryItem(id,requestDTO);
    }
    @MutationMapping
    public String deleteInventory(@PathVariable Long id){
        medicalInventoryService.deleteInventoryItem(id);
        return "Inventory deleted successfully" + id;
    }

}
