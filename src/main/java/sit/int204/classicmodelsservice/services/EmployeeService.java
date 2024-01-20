package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployee(){
        return repository.findAll();
    }

    public Employee getEmployee(String employeeId){
        return repository.findById(employeeId).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee Id " + employeeId + " DOES NOT EXIST !!!") {
                }
        );
    }

    @Transactional
    public Employee newEmployee(Employee employee){
        return repository.save(employee);
    }

    @Transactional
    public void removeEmployee(String employeeId){
        Employee employee = repository.findById(employeeId).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Employee Id " + employeeId + " DOES NOT EXIST !!!") {
                }
        );
        repository.delete(employee);
    }

}
