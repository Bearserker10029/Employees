package com.example.employees.dto;
import com.example.employees.model.Departments;
import com.example.employees.model.Employees;
import com.example.employees.model.Jobs;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesDTO {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double salary;
    private Date hireDate;
    private int managerId;
    private List<Employees> employees;
    private String jobId;
    private int departmentId;
}
