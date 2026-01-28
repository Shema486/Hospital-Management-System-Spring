package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.DepartmentRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DepartmentResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.stereotype.Controller;
import java.util.List;


@Controller
@AllArgsConstructor
public class DepartmentGraphqlController {

    private final DepartmentService departmentService;

    @QueryMapping
    public List<DepartmentResponseDTO> departments() {
        return departmentService.getAllDepartment();
    }

    @QueryMapping
    public DepartmentResponseDTO departmentById(@Argument Long id) {
        return departmentService.findByID(id);
    }

    @MutationMapping
    public DepartmentResponseDTO addDepartment(
            @Valid @Argument DepartmentRequestDTO input) {
        return departmentService.addDepartment(input);
    }

    @MutationMapping
    public DepartmentResponseDTO updateDepartment(
            @Argument Long id,
            @Valid @Argument DepartmentRequestDTO input) {
        return departmentService.updateDepartment(id, input);
    }

    @MutationMapping
    public String deleteDepartment(@Argument Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully with id: " + id;
    }
}

