package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.entities.CustomerA;
import sit.int204.classicmodelsservice.repositories.CustomerARepository;

import java.util.List;

@Service
public class CustomerAService {
    @Autowired
    private CustomerARepository repository;

    public List<CustomerA> insertCustomers(List<CustomerA> customers) {
        if (!customers.isEmpty()) {
            return repository.saveAll(customers);
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT,
                "The List is Empty");
    }

    public List<CustomerA> findAllCustomersA(String fName){
        if (fName == null || fName.isEmpty() || fName.isBlank()){
            return repository.findAll();
        }
        return repository.findByFirstNameContains(fName);
    }

    public List<CustomerA> findAllCustomersA(){
        return findAllCustomersA(null);
    }
}
