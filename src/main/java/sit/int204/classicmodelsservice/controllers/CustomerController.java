package sit.int204.classicmodelsservice.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sit.int204.classicmodelsservice.dtos.NewCustomerDTO;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.exceptions.ErrorResponse;
import sit.int204.classicmodelsservice.exceptions.GeneralException;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
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

    @PostMapping("")
    public NewCustomerDTO createCustomer(@Valid @RequestBody NewCustomerDTO newCustomer) {
        return service.createCustomer(newCustomer);
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

//    @GetMapping("/{customerNumber}")
//    public ResponseEntity<Object> getCustomerByID(@PathVariable Integer customerNumber) {
//        SimpleCustomerDTO simpleCustomerDTO = modelMapper.map(service.getCustomerByID(customerNumber), SimpleCustomerDTO.class);
//        return ResponseEntity.ok(simpleCustomerDTO);
//    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> getCustomerByID(@PathVariable Integer customerNumber) {
        NewCustomerDTO newCustomerDTO = modelMapper.map(service.getCustomerByID(customerNumber), NewCustomerDTO.class);
        return ResponseEntity.ok(newCustomerDTO);
    }
//    @PostMapping("")
//    private Customer newCustomer(@RequestBody Customer customer) {
//        return service.addNewCustomer(customer);
//    }


//    @ExceptionHandler(ItemNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<ErrorResponse> handleItemNotFound(ItemNotFoundException exception, WebRequest request) {
//        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(),
//                request.getDescription(false));
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
//    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Exception handleOther(Exception exception) {
//        GeneralException generalException = new GeneralException(exception.getMessage());
//        return generalException;
//    }
}
