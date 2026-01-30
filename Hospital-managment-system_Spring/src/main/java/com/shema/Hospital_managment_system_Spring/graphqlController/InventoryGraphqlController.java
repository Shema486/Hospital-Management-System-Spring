package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.MedicalInventoryRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.MedicalInventoryResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.MedicalInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@AllArgsConstructor
public class InventoryGraphqlController {

    private final MedicalInventoryService medicalInventoryService;

    @QueryMapping
    public List<MedicalInventoryResponseDTO> inventories() {
        return medicalInventoryService.getAllInventoryItems();
    }

    @MutationMapping
    public MedicalInventoryResponseDTO addInventory(
            @Valid @Argument MedicalInventoryRequestDTO input) {
        return medicalInventoryService.addInventoryItem(input);
    }

    @MutationMapping
    public MedicalInventoryResponseDTO updateInventory(
            @Argument Long id,
            @Valid @Argument MedicalInventoryRequestDTO input) {
        return medicalInventoryService.updateInventoryItem(id, input);
    }

    @MutationMapping
    public String deleteInventory(@Argument Long id) {
        medicalInventoryService.deleteInventoryItem(id);
        return "Inventory deleted successfully with id: " + id;
    }
}

