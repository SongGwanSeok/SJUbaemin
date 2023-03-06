package SJU.SJUbaemin.Domain.Entity.Product;

import SJU.SJUbaemin.Domain.Dto.Product.ProductEnrollRequestDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private Long id;

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviewList = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages = new ArrayList<>();

     */

    @Column(length = 100000)
    private byte[] image;

    //연관관계 편의 메서드
    public void addReview (ProductReview review) {
        this.reviewList.add(review);
        review.setProduct(this);
    }

    public void change(ProductEnrollRequestDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.content = productDto.getContent();
        this.type = productDto.getType();
        this.image = productDto.getImage().getBytes(StandardCharsets.UTF_8);
    }
    public void addImages (List<ProductImage> productImages) {
        productImages.stream().forEach(p -> p.setProduct(this));
    }



}
