package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findByPriceIsBetweenAndProductNameContains(Double lower, Double upper, String productName, Pageable pageable);

    List<Product> findByPriceIsBetweenAndProductNameContains(Double lower, Double upper, String productName, Sort sortBy);

    List<Product> findByPriceIsBetweenAndProductNameContains(Double lower, Double upper, String productName);

    Product findFirstByOrderByPriceDesc();

    List<Product> findByProductLineStartingWith(String line);

    public List<Product> findByPriceIsBetween(Double lower, Double upper);

    public List<Product> findByProductNameContains(String productName);

}