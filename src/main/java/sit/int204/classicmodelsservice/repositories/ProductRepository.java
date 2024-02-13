package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int204.classicmodelsservice.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    public List<Product> findByPriceIsBetweenAndProductNameContains(Double lower, Double upper, String productName);

    List<Product> findByProductLineContains(String name);
    List<Product> findByProductLineStartingWith(String line);
    public List<Product> findByPriceIsBetween(Double lower,Double upper);
    public List<Product> findByProductNameContains(String productName);

}