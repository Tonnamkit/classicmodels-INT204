package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping(("/api/customers"))
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("")
    private List<Customer> getAllCustomers(){
        return service.getAllCustomer();
    }

    @GetMapping("/{customerNumber}")
    private Customer getCustomer(@PathVariable Integer customerNumber){
        return service.getCustomer(customerNumber);
    }

    @GetMapping("/{customerNumber}/orders")
    private List<Order> getCustomerOrders(@PathVariable Integer customerNumber){
        return service.getCustomerOrder(customerNumber);
    }

    @PostMapping("")
    private Customer newCustomer(@RequestBody Customer customer){
        return service.addNewCustomer(customer);
    }
}
