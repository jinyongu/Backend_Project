package com.jinyongu.product_management.domain;

// 비즈니스와 관련된 요구사항
// 다른 계층이나 외부 요소에 의존하지 않도록
public class Product {
    private Long id;
    private String name;
    private Integer price;
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
}