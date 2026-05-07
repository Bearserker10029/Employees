package com.example.employees.dto;

import java.util.Date;

public interface EmployeesDTO {
    int getEmployeeId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    Double getSalary();
    Date gethireDate();
    int getJobId();
    int getManagerId();
    int getDepartmentId();
}