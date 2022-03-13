package com.Nivesh.RestfulWebServicesPart1.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeBeanController {

    @Autowired
    EmployeeDaoService employeeDaoService;

    @GetMapping(path = "/get-employee-id")
    public int getEmployeeId()
    {
        EmployeeBean employeeBean = new EmployeeBean(1, "Nivesh", 23);
        return employeeBean.getId();
    }

    @GetMapping(path = "/get-employee-name")
    public String getEmployeeName()
    {
        EmployeeBean employeeBean = new EmployeeBean(1, "Nivesh", 23);
        return employeeBean.getName();
    }

    //Question 3 solution
    @GetMapping("/Employees")
    List<EmployeeBean> retrieveAllUsers()
    {
        return employeeDaoService.findAll();
    }

    // Question 4 and 6 solution
    @GetMapping("/Employees/{id}")
    public EmployeeBean retrieveOneUser(@PathVariable int id) throws EmployeeNotFoundException {
        EmployeeBean result = employeeDaoService.findOne(id);
        if(result == null)
            throw new EmployeeNotFoundException("id "+id);

        return result;
    }

    // Question 5 Solution
    @PostMapping("/employees")
    public ResponseEntity<Object> createUser(@Valid @RequestBody EmployeeBean user)
    {
        EmployeeBean savedEmployee = employeeDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/Employees/{id}")
    public void deleteUser(@PathVariable int id) throws EmployeeNotFoundException {
        EmployeeBean user = employeeDaoService.deleteById(id);
        if(user == null)
            throw new EmployeeNotFoundException("id "+id);
    }

    @PutMapping("/Employees/{id}/{name}")
    public void updateUser(@PathVariable int id, @PathVariable String name) throws EmployeeNotFoundException {
        EmployeeBean employeeBean = employeeDaoService.updateNameById(id, name);

        if(employeeBean == null)
            throw new EmployeeNotFoundException("id "+id);
    }
}
