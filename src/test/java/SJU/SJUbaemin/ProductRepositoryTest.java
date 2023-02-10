package SJU.SJUbaemin;

import SJU.SJUbaemin.Domain.Category;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import SJU.SJUbaemin.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;


    @Test
    public void 상품아이디검색(){
        Product product = new Product("book1", 20000, 10L, "책 본문", ProductType.BOOK);


        Long savedId = productRepository.save(product);
        Product findProductById = productRepository.findOne(savedId);

        Assertions.assertThat(findProductById).isEqualTo(product);
    }
    
    @Test
    public void 상품카테고리검색(){
        //given
        Product product = new Product("book1", 20000, 10L, "책 본문",  ProductType.BOOK);
        Long savedId = productRepository.save(product);

        //when
        List<Product> findProductByCategory = productRepository.findByCategory(ProductType.BOOK);

        for (Product product1 : findProductByCategory) {
            System.out.println("category type = " + product1.getType());
            System.out.println("product name = " + product1.getName());
        }
        //then

        Assertions.assertThat(findProductByCategory.size()).isEqualTo(1);


    }
}
