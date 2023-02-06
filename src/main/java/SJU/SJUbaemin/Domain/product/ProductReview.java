package SJU.SJUbaemin.Domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ProductReview {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String content;
    private String title;
    private String comment;
    private LocalDate uploadDate;
}
