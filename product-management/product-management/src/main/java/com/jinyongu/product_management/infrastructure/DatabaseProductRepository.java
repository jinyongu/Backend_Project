package com.jinyongu.product_management.infrastructure;

import com.jinyongu.product_management.domain.Product;
import com.jinyongu.product_management.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

// 애플리케이션이 서버에 올라가서 서비스되는 환경에서 실행될 때, 데이터 저장과 애플리케이션 서버 간 데이터 공유
@Repository
@Profile("prod") // 사용자에게 서비스 제공
public class DatabaseProductRepository implements ProductRepository {

    // private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // id를 가져오기 위함

    @Autowired
    public DatabaseProductRepository(/*JdbcTemplate jdbcTemplate*/NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Product add(Product product) {
        /* jdbcTemplate
                .update("INSERT INTO products (name, price, amount) VALUES (?, ?, ?)",
                        product.getName(), product.getPrice(), product.getAmount()); */
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product); // Product getter을 사용

        namedParameterJdbcTemplate.update("INSERT INTO products (name, price, amount) " +
                "VALUES (:name, :price, amount)", namedParameter, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        product.setId(generatedId);

        return product;
    }

    public Product findById(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        // queryForObject : 한 건의 데이터 조회
        Product product = namedParameterJdbcTemplate.queryForObject(
                "SELECT id, name, price, amount FROM products WHERE id=:id",
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)
        );
        return product;
    }

    public List<Product> findAll() {
        // query: 여러 건의 데이터 조회
        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT * FROM products",
                new BeanPropertyRowMapper<>(Product.class)
        );

        return products;
    }

    public List<Product> findByNameContaining(String name) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", "%"+ name + "%");

        List<Product> products = namedParameterJdbcTemplate.query(
                "SELECT * FROM products WHERE name LIKE :name",
                namedParameter,
                new BeanPropertyRowMapper<>(Product.class)
        );
        return products;
    }

    public Product update(Product product) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);

        namedParameterJdbcTemplate.update("UPDATE products SET name=:name, price=:price, amount=:amount WHERE id=:id",
                namedParameter);
        return product;
    }

    public void delete(Long id) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);

        namedParameterJdbcTemplate.update(
                "DELETE FROM products WHERE id=:id",
                namedParameter
        );
    }
}
