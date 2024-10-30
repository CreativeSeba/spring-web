package com.example.firstspringproject;

import com.example.firstspringproject.DbRepository;
import com.example.firstspringproject.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}