package com.example.employees.dto;
import com.example.employees.model.Employees;
import com.example.employees.model.Locations;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DepartmentsDTO {
    private int departmentId;
    private String departmentName;
    private int managerId;
    private int locationId;

}
