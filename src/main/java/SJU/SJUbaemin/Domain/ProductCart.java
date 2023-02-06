package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Domain.Product;
import jakarta.persistence.*;

@Entity
public class ProductCart {

    @Id
    @GeneratedValue
    @Column(name = "product_cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
