package com.jinyongu.product_management.infrastructure;

import com.jinyongu.product_management.domain.Product;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

// 인프라스트럭처에 접근하는 구현 코드들이 위치
// 리스트에 상품을 저장하는 로직이나 데이터베이스에 상품을 저장하는 코드 등을 포함
@Repository
public class ListProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }
}
