package SJU.SJUbaemin.Domain.Dto.Product;

import SJU.SJUbaemin.Domain.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    @NotNull
    private Long id;

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;


}
