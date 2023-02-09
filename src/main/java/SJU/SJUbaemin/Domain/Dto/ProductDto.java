package SJU.SJUbaemin.Domain.Dto;

import SJU.SJUbaemin.Domain.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ProductDto {
    public ProductDto() {
    }

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;


}
