package SJU.SJUbaemin.Domain.Entity.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductContent {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 100000)
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public ProductContent(String content, Product product) {
        this.content = content.getBytes(StandardCharsets.UTF_8);
        this.product = product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }
}
