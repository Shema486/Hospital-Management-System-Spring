package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.MedicalInventory;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.MedicalInventoryDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.MedicalInventoryRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.MedicalInventoryResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MedicalInventoryService {
    private final MedicalInventoryDao inventoryDAO;


    public MedicalInventoryResponseDTO addInventoryItem(MedicalInventoryRequestDTO item) {
        if (item == null){
            throw new BadRequestException("Inventory is required");
        }
        if (isItemNameUnique(item.getItemName(), 0)) {
            throw new BadRequestException("Item name already exists");
        }
        MedicalInventory inventory = new MedicalInventory();
        inventory.setItemName(item.getItemName());
        inventory.setStockQuantity(item.getStockQuantity());
        inventory.setUnitPrice(item.getUnitPrice());
        inventoryDAO.addInventoryItem(inventory);

        return mapToResponse(inventory);

    }

    public MedicalInventoryResponseDTO updateInventoryItem(Long id,MedicalInventoryRequestDTO item) {
        MedicalInventory existing = inventoryDAO.findById(id);
        if (item == null){
            throw new BadRequestException("Inventory is required");
        }
        if (existing==null){
            throw new NotFoundException("Inventory not found with ID:"+id);
        }

        existing.setItemName(item.getItemName());
        existing.setStockQuantity(item.getStockQuantity());
        existing.setUnitPrice(item.getUnitPrice());

        inventoryDAO.updateItem(existing);
        return mapToResponse(existing);

    }

    public void deleteInventoryItem(Long itemId) {
        MedicalInventory inventory = inventoryDAO.findById(itemId);
        if (inventory ==null){
            throw new NotFoundException("Inventory not found");
        }
        inventoryDAO.deleteInventoryItem(itemId);

    }

    public List<MedicalInventoryResponseDTO> getAllInventoryItems() {
        List<MedicalInventory> items = inventoryDAO.findAll();
        if (items.isEmpty()){
            throw new NotFoundException("Inventory not found");
        }
        return items.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }



    public boolean isItemNameUnique(String itemName, long excludeItemId) {
        return inventoryDAO.itemNameExists(itemName, excludeItemId);
    }
    public MedicalInventoryResponseDTO mapToResponse(MedicalInventory inventory){
        return new MedicalInventoryResponseDTO(
                inventory.getItemId(),
                inventory.getItemName(),
                inventory.getStockQuantity(),
                inventory.getUnitPrice()
        );
    }
}
