package com.example.employees.repository;

import com.example.employees.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
}
