package SJU.SJUbaemin.Domain.Entity.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductContent {

    @Id @GeneratedValue
    private Long id;

    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public ProductContent(byte[] bytes, Product product) {
        this.content = bytes;
        this.product = product;
    }

    public ProductContent(byte[] bytes) {
        this.content = bytes;
    }
}
