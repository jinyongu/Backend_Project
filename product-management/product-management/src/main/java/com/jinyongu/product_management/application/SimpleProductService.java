package com.jinyongu.product_management.application;

import com.jinyongu.product_management.domain.Product;
import com.jinyongu.product_management.domain.ProductRepository;
import com.jinyongu.product_management.infrastructure.DatabaseProductRepository;
import com.jinyongu.product_management.infrastructure.ListProductRepository;
import com.jinyongu.product_management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 표현 계층에서 넘겨받은 새로운 자원을 저장하거나 저장되어 있던 자원 조회
// 조회된 도메인 객체의 메서드를 실행
@Service
public class SimpleProductService {

    // private ListProductRepository listProductRepository;
    // private DatabaseProductRepository databaseProductRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;


    @Autowired
    SimpleProductService(/*ListProductRepository listProductRepository*/ /*DatabaseProductRepository databaseProductRepository*/
            ProductRepository productRepository, ModelMapper modelMapper, ValidationService validationService) {
        // this.listProductRepository = listProductRepository;
        // this.databaseProductRepository = databaseProductRepository;
        this.productRepository = productRepository; // ProductRepository.class 참조
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto){
        // ProductDto를 Product로 변환
        Product product = modelMapper.map(productDto, Product.class);

        validationService.checkValid(product);

        // 레포지토리를 호출
        // Product savedProduct = listProductRepository.add(product);
        // Product savedProduct = databaseProductRepository.add(product);
        Product savedProduct = productRepository.add(product);

        // Product를 ProductDto로 변환
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        // ProductDto 반환
        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        // Product product = listProductRepository.findById(id);
        // Product product = databaseProductRepository.findById(id);
        Product product = productRepository.findById(id);
        ProductDto productDto  =modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        // List<Product> products = listProductRepository.findAll();
        // List<Product> products = databaseProductRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        // List<Product> products = listProductRepository.findByNameContaining(name);
        // List<Product> products = databaseProductRepository.findByNameContaining(name);
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    // ModelMapper를 통해 DTO에서 변환된 Product를 수정되기 전의 Product와 바꾸기
    // Product 인스턴스 값 X, Product 자체를 통째로
    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        // Product updatedProduct = listProductRepository.update(product);
        // Product updatedProduct = databaseProductRepository.update(product);
        Product updatedProduct = productRepository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        return updatedProductDto;
    }

    public void delete(Long id) {
        // listProductRepository.delete(id);
        // databaseProductRepository.delete(id);
        productRepository.delete(id);
    }

}
