package SJU.SJUbaemin;


import SJU.SJUbaemin.Domain.Dto.ProductDto;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import SJU.SJUbaemin.Repository.ProductRepository;
import SJU.SJUbaemin.Service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductServiceTest {

    @Autowired ProductRepository productRepository;
    @Autowired ProductService productService;

    @Test
    public void 상품등록() {
        //given
        Product product = new Product("book100", 500000, 500L, "책 본문",  ProductType.BOOK);

        //when
        productService.register(product);

        //then
        Product findProduct = productService.findOne(product.getId());
        Assertions.assertThat(findProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    public void 상품수정() {
        //given
        Product product = new Product("book1", 20000, 10L, "책 본문",  ProductType.BOOK);
        productService.register(product);

        //when
        ProductDto inputDto = new ProductDto("Living", 20000, 10L, "리빙 본문", ProductType.LIVING);
        productService.update(product.getId(), inputDto);


        Product findProduct = productRepository.findOne(product.getId());

        //then
        Assertions.assertThat(findProduct.getName()).isEqualTo(inputDto.getName());
        Assertions.assertThat(findProduct.getPrice()).isEqualTo(inputDto.getPrice());
        Assertions.assertThat(findProduct.getQuantity()).isEqualTo(inputDto.getQuantity());
        Assertions.assertThat(findProduct.getContent()).isEqualTo(inputDto.getContent());
    }

    @Test
    public void 상품삭제() {
        //given
        Product product = new Product("book1", 20000, 10L, "책 본문",  ProductType.BOOK);
        productService.register(product);

        //when
        productService.deregister(product.getId());

        //then
        List<Product> products = productService.findProductsAll();
        Assertions.assertThat(products.size()).isEqualTo(0);
    }

    @Test
    public void 상품타입조회() {
        //given
        Product product1 = new Product("book1", 20000, 10L, "책 본문",  ProductType.BOOK);
        Product product2 = new Product("book2", 30000, 20L, "책",  ProductType.BOOK);
        productService.register(product1);
        productService.register(product2);

        //when
        List<Product> productsCategory = productService.findProductsCategory(ProductType.BOOK);

        for (Product product : productsCategory) {
            System.out.println("product.getName() = " + product.getName());
        }

        //then
        Assertions.assertThat(productsCategory.size()).isEqualTo(2);
    }

}
