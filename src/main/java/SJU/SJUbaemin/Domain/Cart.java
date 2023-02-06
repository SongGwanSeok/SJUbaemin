package SJU.SJUbaemin.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private Long quantity;
    private int price;

}
