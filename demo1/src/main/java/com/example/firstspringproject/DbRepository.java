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
    JdbcTemplate jt;

    public DbRepository(JdbcTemplate jt) {
        this.jt = jt;
    }

    public List<Product> getAllProducts() {
        return jt.query(
                "SELECT * FROM products",
                BeanPropertyRowMapper.newInstance(Product.class)
        );
    }

    public Product getProductById(int id) {
        return jt.queryForObject(
                "SELECT * FROM products WHERE id = " + id,
                BeanPropertyRowMapper.newInstance(Product.class)
        );
    }

    public int insertProduct(Product product) {
        return jt.update(
                "INSERT products VALUES(NULL, ?, ?, ?, ?, ?)",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImg(),
                product.getCategoryId()
        );
    }

    public int updateProduct(Product product) {
        return jt.update(
            "UPDATE products SET name = ?, price = ?, description = ?, img = ?, category_id = ? WHERE id = ?",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImg(),
                product.getCategoryId(),
                product.getId()
        );
    }

    public int deleteProduct(int id) {
        return jt.update(
                "DELETE FROM products WHERE id =" + id
        );
    }

}
