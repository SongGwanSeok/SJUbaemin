package SJU.SJUbaemin.Repository;

import SJU.SJUbaemin.Domain.Dto.ProductDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Domain.Product;
import SJU.SJUbaemin.Domain.ProductType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // final로 선택된 애들을 생성자 주입을 만들어 준다.
public class ProductRepository {

    private final EntityManager em;

    public Product save(Product product) {
        if(product.getId() == null){
            em.persist(product);
        } else {
            em.merge(product);
        }
        return product;
    }

    public void delete(Product product) {
        em.remove(product);
    }

    public Product findOne(Long productId) {
        return em.find(Product.class, productId);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public List<Product> findByName(String name) {
        return em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    //상품 타입으로 찾기
    public List<Product> findByCategory(ProductType type) {
        return em.createQuery("select p from Product p where p.type = :type", Product.class)
                .setParameter("type", type)
                .getResultList();

    }


}
