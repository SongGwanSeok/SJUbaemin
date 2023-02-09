package SJU.SJUbaemin.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String content;

    private String title;
    private String comment;
    private LocalDate uploadDate;
    public void setProduct(Product product) {
        this.product = product;
    }
}
