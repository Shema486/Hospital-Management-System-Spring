package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.PrescriptionItem;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.PrescriptionItemDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PrescriptionItemService {

    private final PrescriptionItemDao dao;

    // ADD item
    public void addItem(PrescriptionItem item) {
        if (item == null) {
            throw new BadRequestException("Prescription item is required");
        }
        dao.add(item);
    }

    // GET ALL items
    public List<PrescriptionItem> getAllItems() {
        List<PrescriptionItem> items = dao.findAll();
        if (items.isEmpty()) {
            throw new NotFoundException("No prescription items found");
        }
        return items;
    }
}
