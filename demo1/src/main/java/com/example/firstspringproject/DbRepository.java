package com.example.firstspringproject;

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

    public int insertMultiple(List<Product> products) {
        // SQL query to insert a single product
        String sql = "INSERT INTO products (name, price, description, img, category_id) VALUES (?, ?, ?, ?, ?)";

        // Prepare the parameters for each product
        List<Object[]> batchArgs = new ArrayList<>(); //Object to typ danych ktory moze byc kazdym prymitywnym typem danch, uzywamy listy Objects w liscie, zeby kazdy indeks list mial wszystkie informacje do updatu

        for (Product product : products) {
            // Create an Object[] for each product containing the values to be inserted
            Object[] params = new Object[]{
                    product.getName(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getImg(),
                    product.getCategoryId()
            };

            // Add to batch
            batchArgs.add(params);
        }

        // Execute the batch update
        return jt.batchUpdate(sql, batchArgs).length;  // zwracamy liczbe wierszy ktore zostaly zmienione
    }


    public int updateProduct(Product product, int id) {
        return jt.update(
                "UPDATE products SET name = ?, price = ?, description = ?, img = ?, category_id = ? WHERE id = ?",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImg(),
                product.getCategoryId(),
                id
        );
    }

    public int deleteProduct(int id) {
        return jt.update(
                "DELETE FROM products WHERE id =" + id
        );
    }

}
