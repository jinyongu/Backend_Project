package com.jinyongu.product_management;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class ProductManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementApplication.class, args);
	}

	// 기본 설정: 매개변수가 없는 생성자로 인스턴스를 생성한 후 setter로 값을 초기화하여 변환
	// setter 없이 Product -> ProductDto
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		return modelMapper;
	}

	// connection pool 초기화
	@Bean
	@Profile("prod")
	public ApplicationRunner runner(DataSource dataSource) {
		return args -> {
			Connection connection = dataSource.getConnection();
		};
	}

}
