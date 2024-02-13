package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProductsByProductLine(String productLine) {
        return repository.findByProductLineStartingWith(productLine);
    }

    public Page<Product> getAllProducts(Double lower, Double upper, String name, String[] sortBy, String[] direction, int pageNo, int pageSize) {
        if (lower <= 0 && upper <= 0) {
            upper = repository.findFirstByOrderByPriceDesc().getPrice();
        }
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy != null && sortBy.length > 0) {
            for (int i = 0; i < sortBy.length; i++) {
                orders.add(new Sort.Order((
                        direction[i].equalsIgnoreCase("asc")
                                ? Sort.Direction.ASC
                                : Sort.Direction.DESC)
                        , sortBy[i]));
            }
        }
        if (pageSize <= 0){
            pageSize = (int) repository.count();
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orders));
        return repository.findByPriceIsBetweenAndProductNameContains(
                lower, upper, name, pageable);
    }

    public List<Product> getAllProducts(Double lower, Double upper, String name, String sortBy, String direction) {
        if (lower <= 0 && upper <= 0) {
            upper = repository.findFirstByOrderByPriceDesc().getPrice();
        }
        if (sortBy.isEmpty()) {
            sortBy = "productCode";
        }
        Sort.Order sortOrder = new Sort.Order((direction.equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC), sortBy);
        return repository.findByPriceIsBetweenAndProductNameContains(
                lower, upper, name, Sort.by(sortOrder));
    }

    public List<Product> getAllProducts(Double lower, Double upper, String name) {
        if (lower <= 0 && upper <= 0) {
            return repository.findByProductNameContains(name);
        } else {
            return repository.findByPriceIsBetweenAndProductNameContains(lower, upper, name);
        }
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
