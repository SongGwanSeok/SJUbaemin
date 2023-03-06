package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Product.ProductEnrollRequestDto;
import SJU.SJUbaemin.Domain.Dto.Product.ProductEnrollResponseDto;
import SJU.SJUbaemin.Domain.Entity.Product.Product;
import SJU.SJUbaemin.Domain.Entity.Product.ProductType;
import SJU.SJUbaemin.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
    @PostMapping(value = "/enroll")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductEnrollResponseDto> enroll(
            @RequestBody @Valid ProductEnrollRequestDto productEnrollRequestDto
            ){
        ProductEnrollResponseDto productEnrollResponseDto = productService.enroll(productEnrollRequestDto);
        return ResponseEntity.ok(productEnrollResponseDto);
    }


    /**
     * 상품 등록
     */

    /*

    @PostMapping(value = "/register")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<ProductResponseDto> register(
            @RequestPart(value = "data", required = false) ProductRequestDto productRequestDto,
            @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
            HttpServletRequest request
    ) throws IOException {


        Product product = productService.register(productRequestDto, multipartFiles);
        List<String> pathList = product.getProductImages().stream().map(i -> i.getPath()).collect(Collectors.toList());
        ProductResponseDto productResponseDto = productEntityToDto(product);
        productResponseDto.setImageFilesPath(pathList);

        return ResponseEntity.ok(productResponseDto);
    }

     */


    /**
     * 상품 목록 전체
     */
    @GetMapping("/all")
    public Result<List> findAll() {
        List<Product> productsAll = productService.findProductsAll();
        List<ProductEnrollResponseDto> collect = productsAll.stream()
                .map(p -> {return new ProductEnrollResponseDto(
                                        p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getContent(),
                                        new String(p.getImage(), StandardCharsets.UTF_8), p.getType()
                                        );
                        }
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

        List<ProductEnrollResponseDto> collect = productsCategory.stream()
                .map(p -> {return new ProductEnrollResponseDto(
                                    p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getContent(),
                                    new String(p.getImage(), StandardCharsets.UTF_8), p.getType()
                            );
                        }
                ).collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
    }

    /**
     * 상품 정보 수정
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductEnrollResponseDto> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductEnrollRequestDto request
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

    public ProductEnrollResponseDto productEntityToDto(Product product) {
        return ProductEnrollResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .quantity(product.getQuantity())
                .type(product.getType())
                .image(new String(product.getImage(), StandardCharsets.UTF_8))
                .build();
    }

}
