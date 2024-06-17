package com.example.criteriaQuerie;

import com.example.criteriaQuerie.Entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl {


    @Autowired
    EmployeeRepository employeeRepository;


    public void saveEmployee(Employee employee) {
        log.info("Saved Employee: {}", employee);
        employeeRepository.save(employee);
    }
   public void saveEmployees(List<Employee> employees) {
        log.info("Saved Employees: {}", employees);

       employeeRepository.saveAll(employees);
    }



}
