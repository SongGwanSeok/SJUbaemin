package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    public Product(String name, int price, Long quantity, String content, ProductType type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.content = content;
        this.type = type;
    }

    public void change(ProductDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
        this.content = productDto.getContent();
    }



}
