package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.models.ProductPage;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(
            @RequestParam(required = false, defaultValue = "0") Double lower,
            @RequestParam(required = false, defaultValue = "0") Double upper,
            @RequestParam(required = false, defaultValue = "") String productName,
            @RequestParam(required = false, defaultValue = "") String[] sortBy,
            @RequestParam(required = false, defaultValue = "asc") String[] direction,
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "0") int pageSize) {
        if (pageSize == 0){
            return ResponseEntity.ok(service.getAllProducts());
        }else{
            Page<Product> page = service.getAllProducts(lower, upper, productName, sortBy, direction, pageNo, pageSize);
            ProductPage pp = new ProductPage();
            pp.setProductList(page.getContent());
            pp.setPageNumber(page.getNumber());
            pp.setPageSize(page.getSize());
            pp.setTotalElements((int) page.getTotalElements());
            pp.setTotalPages(page.getTotalPages());
            return ResponseEntity.ok().body(pp);
        }

    }

    @GetMapping("/product-line/{id}")
    public List<Product> getAllProductsLine(@PathVariable String id) {
        return service.getAllProductsByProductLine(id);
    }

}
