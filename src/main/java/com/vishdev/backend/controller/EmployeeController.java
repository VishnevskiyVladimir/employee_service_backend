package com.vishdev.backend.controller;

import com.vishdev.backend.exception.ResourceNotFoundException;
import com.vishdev.backend.model.Employee;
import com.vishdev.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //TODO: check statuses of all methods (look JSON API and Over...(German girl))
    //TODO: what if db is dropped
    //TODO: no content status 204
    @GetMapping("employees")
    public List<Employee> returnAllEmployees () {
        return employeeRepository
                .findAll();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> returnEmployeeById (@PathVariable long id) throws ResourceNotFoundException{
        Employee employeeFromRepo = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ id + "is not found."));
        return new ResponseEntity<>(employeeFromRepo, HttpStatus.OK);
    }


    //TODO: if already exists or constraints violated
    @PostMapping("employees")
    public ResponseEntity<Employee> addEmployee (@RequestBody Employee employeeRequestBody) {
        Employee employeeFromRepo;
        try {
            employeeFromRepo = employeeRepository
                    .save(employeeRequestBody);
        } catch (Throwable e) {
            //e.printStackTrace();
            throw new RuntimeException();
        }
        return new ResponseEntity<>(employeeFromRepo, HttpStatus.CREATED);
    }

    //TODO: if already exists or constraints violated
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> changeEmployeeById (@PathVariable long id,@RequestBody Employee employeeRequestBody ) throws ResourceNotFoundException{
        Employee employeeFromRepo = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ id + "is not found."));

        employeeFromRepo.setFirstName(employeeRequestBody.getFirstName());
        employeeFromRepo.setLastName(employeeRequestBody.getLastName());
        employeeFromRepo.setEmailId(employeeRequestBody.getEmailId());

        try {
            employeeFromRepo = employeeRepository
                    .save(employeeFromRepo);
        } catch (Throwable e) {
            //e.printStackTrace();
            throw new RuntimeException();
        }

        return new ResponseEntity<>(employeeFromRepo, HttpStatus.OK);
    }

    @DeleteMapping("employees/{id}")
    public void deleteEmployeeById (@PathVariable long id) throws ResourceNotFoundException{
        Employee employeeFromRepo = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id "+ id + "is not found."));
        employeeRepository.delete(employeeFromRepo);
    }

}
