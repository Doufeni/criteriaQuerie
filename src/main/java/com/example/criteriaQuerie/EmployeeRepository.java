package com.example.criteriaQuerie;

import com.example.criteriaQuerie.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Override
    Optional<Employee> findById(Long id);

    @Override
    void deleteById(Long id);


}
