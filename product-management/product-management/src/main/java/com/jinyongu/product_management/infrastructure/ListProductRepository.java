package com.jinyongu.product_management.infrastructure;

import com.jinyongu.product_management.domain.EntityNotFoundException;
import com.jinyongu.product_management.domain.Product;
import com.jinyongu.product_management.domain.ProductRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// 인프라스트럭처에 접근하는 구현 코드들이 위치
// 리스트에 상품을 저장하는 로직이나 데이터베이스에 상품을 저장하는 코드 등을 포함
// 애플리케이션이 로컬 개발환경에서 실행될 때, 개발 중 기능 테스트
@Repository
@Profile("test") // 테스트를 위한 환경
public class ListProductRepository implements ProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Not Found Product"));
        // Optional 객체가 비어있으면 'NoSuchElementException', Product가 들어있으면 Product
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
