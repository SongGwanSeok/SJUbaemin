package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Dto.Product.ProductRequestDto;
import SJU.SJUbaemin.Domain.Dto.Product.ProductResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id @GeneratedValue
    @Column(name = "Product_id")
    private Long id;

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;
     */

    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviewList = new ArrayList<>();

    //연관관계 편의 메서드
    public void addReview (ProductReview review) {
        this.reviewList.add(review);
        review.setProduct(this);
    }

    public void change(ProductRequestDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.content = productDto.getContent();
        this.type = productDto.getType();
    }



}
