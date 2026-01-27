package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.AppointmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.AppointmentResponseDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.UpdateAppointmentStatusDTO;
import com.shema.Hospital_managment_system_Spring.service.AppointmentService;
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

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AppointmentGraphqlController {
    private AppointmentService appointmentService;

    @QueryMapping
    public List<AppointmentResponseDTO> appointments() {
        return appointmentService.getAllAppointments();
    }
    @MutationMapping
    public AppointmentResponseDTO addAppointment(
            @Valid @Argument AppointmentRequestDTO requestDTO) {
        return appointmentService.addAppointment(requestDTO);
    }
    @MutationMapping
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return  "Appointment deleted successfully" + id;
    }
    @MutationMapping
    public AppointmentResponseDTO updateAppointment(
            @Argument Long id,
            @Argument("input") UpdateAppointmentStatusDTO input) {

        return appointmentService.updateStatus(id, input.getStatus());
    }

}
