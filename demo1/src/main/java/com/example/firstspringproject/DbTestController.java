package com.example.firstspringproject;

import com.example.firstspringproject.DbRepository;
import com.example.firstspringproject.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class DbTestController {
    DbRepository dr;
    public DbTestController(DbRepository dr) {
        this.dr = dr;
    }
    @GetMapping("/dbtest")
    public List<Product> dbTest() {
        return dr.getAllProducts();
    }

    @GetMapping("/api/products")
    public List<Product> getAll() {
        return dr.getAllProducts();
    }
    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return dr.getProductById(id);
    }
    @PostMapping("/api/products")
    public int insertProduct(@RequestBody Product product) {
        return dr.insertProduct(product);
    }
    @PostMapping("/api/products/multiple")
    public int insertMultiple(@RequestBody List<Product> products) {
        return dr.insertMultiple(products);
    }
    @DeleteMapping("/api/products/{id}")
    public int deleteProduct(@PathVariable int id) {
        return dr.deleteProduct(id);
    }
    @PutMapping("/api/products/{id}")
    public int updateProduct(@PathVariable int id, @RequestBody Product product) {
        return dr.updateProduct(product, id);
    }
}