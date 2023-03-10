package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Dto.ProductDto;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import SJU.SJUbaemin.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 등록
     */
    public Long register(Product product) {
        validationDuplicateProductName(product);
        productRepository.save(product);
        return product.getId();
    }

    /**
     * 상품 삭제
     * check box로 상품의 id 번호를 가져와서 삭제
     */
    public void deregister(Long productId) {
        Product product = productRepository.findOne(productId);
        productRepository.delete(product);
    }

    /**
     * 상품 찾기
     */
    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    public List<Product> findProductsAll() {
        return productRepository.findAll();
    }

    public List<Product> findProductsCategory(ProductType type) {
        return productRepository.findByCategory(type);
    }



    /**
     * 상품 수정
     */
    public Product update (Long productId, ProductDto productDto) {
        Product findProduct = productRepository.findOne(productId);
        findProduct.change(productDto);
        return findProduct;
    }
    

    //product가 있는지 확인
    private void validationDuplicateProductName(Product product) {
        List<Product> products = productRepository.findByName(product.getName());
        if (!products.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다."); // 예외처리
        }
    }

}
