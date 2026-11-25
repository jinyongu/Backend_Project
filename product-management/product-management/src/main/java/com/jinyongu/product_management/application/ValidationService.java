package com.jinyongu.product_management.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

// 유효성 검사 진행 명령(도메인 지식 X)
@Service
@Validated // @Valid가 붙은 메서드 매개변수의 유효성 검사
public class ValidationService {
    public <T> void checkValid(@Valid T validationTarget) {
        // do nothing
        // chckValid 호출하는 것만으로 유효성에 대한 검증
    }
}
