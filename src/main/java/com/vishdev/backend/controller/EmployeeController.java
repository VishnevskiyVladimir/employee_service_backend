package com.vishdev.backend.controller;

import com.vishdev.backend.exception.ResourceNotFoundException;
import com.vishdev.backend.model.Employee;
import com.vishdev.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("employees/")
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

//    @PostMapping()
//    public Employee returnEmployeeById (@RequestParam) {
//        return employeeRepository
//                .findById(id)
//                .orElse(new Employee("1","2","3"));
//
//    }

}
