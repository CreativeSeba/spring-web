package com.example.firstspringproject;
import com.example.firstspringproject.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DbRepository {
    //new DbRepository(new JdbcTemplate());
    JdbcTemplate  jt;
    public DbRepository(JdbcTemplate jt) {
        this.jt = jt;
    }

    public List<Product> getAllProducts() {
        return jt.query(
                "SELECT * FROM products",
                BeanPropertyRowMapper.newInstance(Product.class)
        );
    }
    public List<Product> getProductById(int id) {
        return jt.query(
                "SELECT * FROM products WHERE id = " + id,
                BeanPropertyRowMapper.newInstance(Product.class)
        );
    }
}
