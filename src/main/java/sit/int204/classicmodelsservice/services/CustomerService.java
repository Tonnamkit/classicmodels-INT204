package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerByID(Integer customerNumber) {
        return repository.findById(customerNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerNumber + " DOES NOT EXIST !!!") {
                }
        );
    }

    public Set<Order> getCustomerOrder(Integer customerNumber) {
        return repository.findById(customerNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerNumber + " DOES NOT EXIST !!!"))
                .getOrders();
    }

    public Page<Customer> getCustomers(int page,int size){
     return repository.findAll(PageRequest.of(page,size));
    }


    @Transactional
    public Customer addNewCustomer(Customer customer){
        return repository.save(customer);
    }


}
