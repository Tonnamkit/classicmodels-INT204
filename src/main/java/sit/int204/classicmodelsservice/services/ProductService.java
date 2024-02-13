package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProductsByProductLine(String productLine){
        return repository.findByProductLineStartingWith(productLine);
    }
    public List<Product> getAllProducts(Double lower, Double upper, String name) {
        if (upper - lower > 0) return repository.findByPriceIsBetween(lower, upper);
        else if (name != null) {
            return repository.findByProductNameContains(name);
        }
        return repository.findAll();
    }


}
