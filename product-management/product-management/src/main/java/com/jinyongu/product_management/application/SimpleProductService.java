package com.jinyongu.product_management.application;

import com.jinyongu.product_management.domain.Product;
import com.jinyongu.product_management.infrastructure.ListProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 표현 계층에서 넘겨받은 새로운 자원을 저장하거나 저장되어 있던 자원 조회
// 조회된 도메인 객체의 메서드를 실행
@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;

    @Autowired
    SimpleProductService(ListProductRepository listProductRepository) {
        this.listProductRepository = listProductRepository;
    }

    public Product add(Product product){
        Product savedProduct = listProductRepository.add(product);
        return savedProduct;
    }

}
