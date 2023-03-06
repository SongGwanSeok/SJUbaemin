package SJU.SJUbaemin.Domain.Dto.Product;

import SJU.SJUbaemin.Domain.Entity.Product.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEnrollRequestDto {

    private String name;
    private int price;
    private Long quantity;
    private String content;
    private String image;

    @Enumerated(EnumType.STRING)
    private ProductType type;
}
