package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.entity.SystemUser;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.LoginRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.SystemUserRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.LoginResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.SystemUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class SystemUserGraphQL {

    private final SystemUserService service;

    @QueryMapping
    public List<SystemUser> getAllUsers() {
        return service.getAll();
    }

    @MutationMapping
    public SystemUser createUser(@Valid @Argument SystemUserRequestDTO input) {
        return service.registerUser(input);
    }

    @MutationMapping
    public LoginResponseDTO login(@Valid @Argument LoginRequestDTO input) {
        return service.login(input.getUsername(),input.getPassword());
    }
}

