package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.ProductDto;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import SJU.SJUbaemin.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    /**
     * 상품 등록
     */
    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDto> register(@RequestBody @Valid ProductDto request) {

        Product product = productService.register(request);

        ProductDto response = ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .content(product.getContent())
                .type(product.getType())
                .build();

        return ResponseEntity.ok(response);
    }


    /**
     * 상품 목록 전체
     */
    @GetMapping("/all")
    public Result<List> findAll() {
        List<Product> productsAll = productService.findProductsAll();
        List<ProductDto> collect = productsAll.stream()
                .map(p -> new ProductDto(p.getName(), p.getPrice(), p.getQuantity(), p.getContent(), p.getType()))
                .collect(Collectors.toList());
        return new Result<>(collect.size(), collect);
    }

    /**
     * 상품 목록 타입
     */
    @GetMapping("/type/{type}")
    public Result<List> findType(
            @PathVariable("type") ProductType type
            ) {
        List<Product> productsCategory = productService.findProductsCategory(type);
        List<ProductDto> collect = productsCategory.stream()
                .map(p -> new ProductDto(p.getName(), p.getPrice(), p.getQuantity(), p.getContent(), p.getType()))
                .collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
    }

    /**
     * 상품 정보 수정
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductDto request
    ){
        productService.update(id, request);
        Product product = productService.findOne(id);
        ProductDto response = ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .content(product.getContent())
                .type(product.getType())
                .build();

        return ResponseEntity.ok(response);
    }


    /**
     * 상품 삭제
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> delete(
            @PathVariable("id") Long id
    ) {
        productService.deregister(id);
        return ResponseEntity.ok(id);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

}
