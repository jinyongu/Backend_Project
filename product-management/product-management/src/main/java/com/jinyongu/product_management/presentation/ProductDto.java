package com.jinyongu.product_management.presentation;

import jakarta.validation.constraints.NotNull;

// Product의 필드가 변경 -> 표현, 인프라스트럭처 계층까지 전 계층이 변경되는 것을 방지
// 데이터 자체의 유효성 검사
public class ProductDto {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
