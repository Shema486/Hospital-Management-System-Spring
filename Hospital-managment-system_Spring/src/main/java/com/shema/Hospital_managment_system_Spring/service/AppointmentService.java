package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Appointment;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.AppointmentDao;
import com.shema.Hospital_managment_system_Spring.repository.DoctorDao;
import com.shema.Hospital_managment_system_Spring.repository.PatientDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.AppointmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.AppointmentResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final PatientDao patientDao;


    public AppointmentResponseDTO addAppointment(AppointmentRequestDTO request) {

        if (request == null){
            throw new BadRequestException("failed to add appointment");
        }
        if (!patientDao.existsById(request.getPatientId())) {
            throw new BadRequestException("Invalid patient ID");
        }
        if (!doctorDao.existsById(request.getDoctorId())) {
            throw new BadRequestException("Invalid doctor ID");
        }

        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setPatient(request.getPatient());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setDoctor(request.getDoctor());
        appointment.setStatus(request.getStatus());
        appointment.setReason(request.getReason());

        appointmentDao.addAppointment(appointment);
        return mapToResponse(appointment);
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentDao.findAll();
        if(appointments.isEmpty()){
            throw new NotFoundException("Appointment is not found");

        }
        return  appointments.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteAppointment(Long appointmentId) {
        Appointment existing = appointmentDao.searchById(appointmentId);
        if (existing ==null){
            throw new NotFoundException("appointment not found with ID:" + appointmentId);
        }
        appointmentDao.deleteAppointment(appointmentId);
    }

    public void updateStatus(Long appointmentId, String status) {
        Appointment existing = appointmentDao.searchById(appointmentId);
        if (existing ==null || status ==null){
            throw new NotFoundException("appointment not found with ID:" + appointmentId);
        }
        appointmentDao.updateStatus(appointmentId, status);
    }
    public AppointmentResponseDTO mapToResponse(Appointment appointment){
        return new AppointmentResponseDTO(
                appointment.getAppointmentId(),
                appointment.getPatientId(),
                appointment.getPatient(),
                appointment.getDoctorId(),
                appointment.getDoctor(),
                appointment.getAppointmentDate(),
                appointment.getStatus(),
                appointment.getReason()
        );
    }
}

