package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomer() {
        return repository.findAll();
    }

    public Customer getCustomer(Integer customerNumber) {
        return repository.findById(customerNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerNumber + " DOES NOT EXIST !!!") {
                }
        );
    }

    public List<Order> getCustomerOrder(Integer customerNumber) {
        return repository.findById(customerNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerNumber + " DOES NOT EXIST !!!"))
                .getOrderList();
    }

    @Transactional
    public Customer addNewCustomer(Customer customer){
        return repository.save(customer);
    }
}
