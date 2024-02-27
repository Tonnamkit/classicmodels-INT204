package sit.int204.classicmodelsservice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.dtos.PageDTO;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.CustomerService;
import sit.int204.classicmodelsservice.services.ListMapper;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(("/api/customers"))
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    private ResponseEntity<Object> getAllCustomers(
            @RequestParam(defaultValue = "false") boolean pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (pageable) {
            Page<Customer> customerPage = service.getCustomers(page, pageSize);
            return ResponseEntity.ok(listMapper.toPageDTO(customerPage, SimpleCustomerDTO.class, modelMapper));
        } else {
            return ResponseEntity.ok(listMapper.mapList(service.getCustomers(), SimpleCustomerDTO.class, modelMapper));
        }
    }
//    @GetMapping("")
//    private List<Customer> getAllCustomers(){
//        return service.getAllCustomer();
//    }

//    @GetMapping("/{customerNumber}")
//    private Customer getCustomer(@PathVariable Integer customerNumber){
//        return service.getCustomerByID(customerNumber);
//    }

    @GetMapping("/{customerNumber}/orders")
    private Set<Order> getCustomerOrders(@PathVariable Integer customerNumber) {
        return service.getCustomerOrder(customerNumber);
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> getCustomerByID(@PathVariable Integer customerNumber) {
        SimpleCustomerDTO simpleCustomerDTO = modelMapper.map(service.getCustomerByID(customerNumber), SimpleCustomerDTO.class);
        return ResponseEntity.ok(simpleCustomerDTO);
    }

    @PostMapping("")
    private Customer newCustomer(@RequestBody Customer customer) {
        return service.addNewCustomer(customer);
    }
}
