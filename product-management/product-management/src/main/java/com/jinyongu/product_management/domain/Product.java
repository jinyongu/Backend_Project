package com.jinyongu.product_management.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

// 비즈니스와 관련된 요구사항
// 다른 계층이나 외부 요소에 의존하지 않도록
// 도메인 지식에 대한 유효성 검사
public class Product {
    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Max(1_000_000)
    @Min(0)
    private Integer price;

    @Max(9_999)
    @Min(0)
    private Integer amount;

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }
}