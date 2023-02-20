package SJU.SJUbaemin.Domain.Dto;

import SJU.SJUbaemin.Domain.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;


}
