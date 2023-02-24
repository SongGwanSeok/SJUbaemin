package SJU.SJUbaemin.Domain.Dto.Product;

import SJU.SJUbaemin.Domain.ProductImage;
import SJU.SJUbaemin.Domain.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.File;
import java.util.List;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    @NotNull
    private Long id;

    private String name;
    private int price;
    private Long quantity;
    private String content;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private List<String> imageFilesPath;

}
