package com.example.employees.repository;

import com.example.employees.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JobsRepository extends JpaRepository<Jobs, String> {
}
