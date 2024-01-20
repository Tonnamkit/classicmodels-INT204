package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(("/api/employees"))
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("")
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId){
        return service.getEmployee(employeeId);
    }
}
