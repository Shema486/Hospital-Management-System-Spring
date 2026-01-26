package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentResponseDTO {
    private Long deptId;
    private int locationFloor;
    private String deptName;
}
