package sit.int204.classicmodelsservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import sit.int204.classicmodelsservice.dtos.NewCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.exceptions.ItemNotFoundException;
import sit.int204.classicmodelsservice.services.ListMapper;
import sit.int204.classicmodelsservice.repositories.CustomerRepository;

import java.util.List;
import java.util.Set;



@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ListMapper listMapper;
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerByID(Integer customerNumber) {
        return repository.findById(customerNumber).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer Number " + customerNumber + " DOES NOT EXIST !!!") {
                }
//                () -> new ItemNotFoundException("Customer ID " + customerNumber + " DOES NOT EXIST !!!") {
//                }
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

    public NewCustomerDTO createCustomer(NewCustomerDTO newCustomer) {
        if(repository.existsById(newCustomer.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate customer for id "+
                    newCustomer.getId());
        }
        Customer customer = mapper.map(newCustomer, Customer.class);
        return mapper.map(repository.saveAndFlush(customer), NewCustomerDTO.class);
    }
    public List<NewCustomerDTO> getAllCustomers() {
        return listMapper.mapList(repository.findAll(), NewCustomerDTO.class, mapper);
    }
}
