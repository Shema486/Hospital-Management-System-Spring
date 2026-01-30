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

import org.springframework.stereotype.Controller;


import java.util.List;


@Controller
@AllArgsConstructor
public class AppointmentGraphqlController {

    private final AppointmentService appointmentService;

    @QueryMapping
    public List<AppointmentResponseDTO> appointments() {
        return appointmentService.getAllAppointments();
    }

    @MutationMapping
    public AppointmentResponseDTO addAppointment(
            @Argument @Valid AppointmentRequestDTO input) {
        return appointmentService.addAppointment(input);
    }

    @MutationMapping
    public String deleteAppointment(@Argument Long id) {
        appointmentService.deleteAppointment(id);
        return "Appointment deleted successfully";
    }

    @MutationMapping
    public AppointmentResponseDTO updateAppointment(
            @Argument Long id,
            @Argument UpdateAppointmentStatusDTO input) {

        return appointmentService.updateStatus(id, input.getStatus());
    }

}