package SJU.SJUbaemin.Domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Product {

    @Id @GeneratedValue
    @Column(name = "Product_id")
    private Long id;

    private String name;
    private int price;
    private Long quantity;
    private String content;

}
