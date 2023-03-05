package SJU.SJUbaemin.Domain.Entity.Cart;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;
//
//    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
//    private Member member;

    private Long quantity;
    private int price;

}
