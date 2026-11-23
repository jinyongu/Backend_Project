package com.jinyongu.product_management.presentation;

// Product의 필드가 변경 -> 표현, 인프라스트럭처 계층까지 전 계층이 변경되는 것을 방지
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }
}
