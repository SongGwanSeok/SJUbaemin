package SJU.SJUbaemin.Domain.Entity.Order;

import SJU.SJUbaemin.Domain.Entity.Product.Product;
import jakarta.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    private Long quantity;
    private int amountPrice;


}
