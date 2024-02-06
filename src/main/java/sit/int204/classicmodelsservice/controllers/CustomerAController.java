package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.CustomerA;
import sit.int204.classicmodelsservice.services.CustomerAService;

import java.util.List;

@RestController
@RequestMapping("/api/customerAs")
public class CustomerAController {
    @Autowired
    private CustomerAService service;

    @PostMapping("")
    public List<CustomerA> addNewCustomerAList(@RequestBody List<CustomerA> customerAList){
        return service.insertCustomers(customerAList);
    }

    @GetMapping("")
    public List<CustomerA> customerAListContainFirstName(@RequestParam(required = false) String fName){
        return service.findAllCustomersA(fName);
    }
}
