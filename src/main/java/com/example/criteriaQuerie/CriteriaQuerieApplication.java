package com.example.criteriaQuerie;

import com.example.criteriaQuerie.Entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class CriteriaQuerieApplication implements CommandLineRunner {


	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@Autowired
	EmployeeSearchDAO employeeSearchDAO;
	public List<Employee> Employees = new ArrayList<>();
	public Employee emp1 = new Employee("saif", "doufani", "a@gmail.com");
	public Employee emp2 = new Employee("saif1", "doufani1", "b@gmail.com");
	public Employee emp3 = new Employee("saif3", "doufani2", "c@gmail.com");
	public Employee emp4 = new Employee("saif4", "doufani3", "d@gmail.com");

	public static void main(String[] args) {
		SpringApplication.run(CriteriaQuerieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employees.add(emp1);
		Employees.add(emp2);
		Employees.add(emp3);
		Employees.add(emp4);


		employeeServiceImpl.saveEmployees(Employees);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setFirstname(emp4.getFirstName());
		List<Employee> employees =  employeeSearchDAO.findAllByCriteria(searchRequest);

		log.info("lets celebrate: {}", employees);


	}
}
