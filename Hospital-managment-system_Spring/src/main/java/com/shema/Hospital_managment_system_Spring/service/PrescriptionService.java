package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Appointment;
import com.shema.Hospital_managment_system_Spring.entity.Prescription;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.AppointmentDao;
import com.shema.Hospital_managment_system_Spring.repository.PrescriptionDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.PrescriptionRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PrescriptionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionDao prescriptionDAO;
    private final AppointmentDao appointmentDao;

    public PrescriptionResponseDTO addPrescription(PrescriptionRequestDTO dto)  {
        if(dto == null){
            throw new BadRequestException("failed to add prescription");
        }
        if (!appointmentDao.existById(dto.getAppointmentId())){
            throw new BadRequestException("Invalid Appointment ID");
        }
        if (hasPrescriptionForAppointment(dto.getAppointmentId())) {
            throw new BadRequestException(
                    "This appointment already has a prescription"
            );
        }
        Prescription prescription = new Prescription();
        prescription.setAppointmentId(dto.getAppointmentId());
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setNotes(dto.getNotes());

        prescriptionDAO.addPrescription(prescription);
        return mapToResponse(prescription);
    }

    public List<PrescriptionResponseDTO> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionDAO.findAll();
        if (prescriptions.isEmpty()){
            throw new NotFoundException("No Prescription found");
        }
        return prescriptions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void updatePrescriptionNotes(Long id, String notes) {
        if(notes == null ){
            throw new BadRequestException("Prescription data is required");
        }
        Prescription existing = prescriptionDAO.findById(id);
        if (existing ==null){
            throw new NotFoundException("Prescription not found");
        }
        prescriptionDAO.updatePrescription(id, notes);

    }

    public void deletePrescription(Long id) {
        Prescription existing = prescriptionDAO.findById(id);
        if (existing == null){
            throw  new NotFoundException("Prescription not with ID:"+id);
        }
        prescriptionDAO.deletePrescription(id);

    }
    public boolean hasPrescriptionForAppointment(Long appointmentId) {
        if (appointmentId == null) {
            return false;
        }
        return !prescriptionDAO.findByAppointmentId(appointmentId).isEmpty();
    }

     public  PrescriptionResponseDTO mapToResponse (Prescription prescription){
        return new PrescriptionResponseDTO(

                prescription.getPrescriptionId(),
                prescription.getAppointmentId(),
                prescription.getPrescriptionDate(),
                prescription.getNotes()

        );
     }


}
