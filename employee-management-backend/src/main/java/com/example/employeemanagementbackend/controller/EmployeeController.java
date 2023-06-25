package com.example.employeemanagementbackend.controller;

import com.example.employeemanagementbackend.exception.ResourceNotFoundException;
import com.example.employeemanagementbackend.model.Employee;
import com.example.employeemanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all the employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // insert an employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee Does Not Exist with ID: " + id)
        );
        return ResponseEntity.ok(employee);
    }

    // update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee Does Not Exist with ID: " + id)
        );

        oldEmployee.setFirstName((newEmployee.getFirstName()));
        oldEmployee.setLastName(newEmployee.getLastName());
        oldEmployee.setEmailId(newEmployee.getEmailId());

        Employee updatedEmployee = employeeRepository.save(oldEmployee);

        return ResponseEntity.ok(updatedEmployee);
    }

}
























