package com.jinyongu.product_management.domain;

import java.util.List;

// 다른 모든 계층은 infrastructure 계층에 의존 X
public interface ProductRepository {
    Product add(Product product);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByNameContaining(String name);
    Product update(Product product);
    void delete(Long id);
}
