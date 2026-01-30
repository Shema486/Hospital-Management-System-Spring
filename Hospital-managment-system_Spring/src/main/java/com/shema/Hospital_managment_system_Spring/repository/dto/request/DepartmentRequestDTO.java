package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentRequestDTO {

    @NotNull(message = "Location floor is required")
    @Min(value = 1, message = "Location floor must be at least 1")
    private int locationFloor;
    @NotBlank(message = "Department name  is required")
    private String deptName;
}
