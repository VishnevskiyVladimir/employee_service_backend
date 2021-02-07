package com.vishdev.backend.controller;

import com.vishdev.backend.exception.ResourceNotFoundException;
import com.vishdev.backend.model.Employee;
import com.vishdev.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("employees")
    public List<Employee> returnAllEmployees () {
        return employeeRepository
                .findAll();
    }

    @GetMapping("employees/{id}")
    public Employee returnEmployeeById (@PathVariable long id) throws ResourceNotFoundException{
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ id + "is not found."));
    }


    //TODO: изменить код ответа на 201 Created. And change 500 to Already exists. probably ResponseEntity is needed
    @PostMapping("employees")
    public Employee addEmployee (@RequestBody Employee employee) {
        return employeeRepository
                .save(employee);

    }

}
