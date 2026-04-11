package com.example.employees.repository;

import com.example.employees.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query("""
            select distinct e
            from Employees e
            left join fetch e.jobId j
            left join fetch e.departmentId d
            left join fetch d.locationId l
            left join fetch e.managerId m
            where e.employeeId = :id
            """)
    Optional<Employees> findByIdWithRelations(@Param("id") int id);

    @Query("""
            select distinct e
            from Employees e
            left join fetch e.jobId j
            left join fetch e.departmentId d
            left join fetch d.locationId l
            where
                (lower(e.firstName) like lower(concat('%', :termino, '%')) or
                 lower(e.lastName) like lower(concat('%', :termino, '%')) or
                 lower(j.jobTitle) like lower(concat('%', :termino, '%')) or
                 lower(d.departmentName) like lower(concat('%', :termino, '%')) or
                 lower(l.city) like lower(concat('%', :termino, '%')))
            order by e.employeeId
            """)
    List<Employees> searchEmployees(@Param("campo") String campo, @Param("termino") String termino);
}
