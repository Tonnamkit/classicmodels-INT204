package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.entities.Product;
import sit.int204.classicmodelsservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("")
    public List<Product> getAllProducts(@RequestParam(required = false,defaultValue = "0") Double lower, @RequestParam(required = false,defaultValue = "0") Double upper, @RequestParam(required = false) String productName){
       return service.getAllProducts(lower, upper, productName);
    }

    @GetMapping("/product-line/{id}")
    public  List<Product> getAllProducts(@PathVariable String id){
        return service.getAllProductsByProductLine(id);
    }

}
