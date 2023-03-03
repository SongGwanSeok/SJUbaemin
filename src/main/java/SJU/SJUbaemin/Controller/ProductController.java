package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Product.ProductRequestDto;
import SJU.SJUbaemin.Domain.Dto.Product.ProductResponseDto;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import SJU.SJUbaemin.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    /**
     * 상품 등록
     */
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<ProductResponseDto> register(
            @RequestPart(value = "data", required = false) ProductRequestDto productRequestDto,
            @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
            HttpServletRequest request
    ) throws IOException {

        log.info("contents-type : {}", request.getContentType());
        log.info("multipart : {}", multipartFiles.get(0).getContentType());
        log.info("multipart : {}", multipartFiles);


        Product product = productService.register(productRequestDto, multipartFiles);
        List<String> pathList = product.getProductImages().stream().map(i -> i.getPath()).collect(Collectors.toList());
        ProductResponseDto productResponseDto = productEntityToDto(product);
        productResponseDto.setImageFilesPath(pathList);

        return ResponseEntity.ok(productResponseDto);
    }


    /**
     * 상품 목록 전체
     */
    @GetMapping("/all")
    public Result<List> findAll() {
        List<Product> productsAll = productService.findProductsAll();
        List<ProductResponseDto> collect = productsAll.stream()
                .map(p -> new ProductResponseDto(
                        p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getContent(), p.getType(),
                        p.getProductImages().stream().map(productImage -> productImage.getPath()).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
    }

    /**
     * 상품 목록 타입
     */
    @GetMapping("/type/{type}")
    public Result<List> findType(@PathVariable("type") ProductType type) {
        List<Product> productsCategory = productService.findProductsCategory(type);
        List<ProductResponseDto> collect = productsCategory.stream()
                .map(p -> new ProductResponseDto(
                        p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getContent(), p.getType(),
                        p.getProductImages().stream().map(productImage -> productImage.getPath()).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
    }

    /**
     * 상품 정보 수정
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductRequestDto request
    ){
        productService.update(id, request);
        Product product = productService.findOne(id);
        return ResponseEntity.ok(productEntityToDto(product));
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

    public ProductResponseDto productEntityToDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .quantity(product.getQuantity())
                .type(product.getType())
                .build();
    }

}
