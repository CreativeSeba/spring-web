package com.example.firstspringproject;

import com.example.firstspringproject.DbRepository;
import com.example.firstspringproject.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/api/product")
    public List<Product> getAll() {
        return dr.getAllProducts();
    }
    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return dr.getProductById(id);
    }

    @PostMapping("/api/products")
    public ModelAndView insertProduct(@ModelAttribute Product product) {
        dr.insertProduct(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/api/products2")
    public Product insertProduct2(@ModelAttribute Product product) {
        return product;
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