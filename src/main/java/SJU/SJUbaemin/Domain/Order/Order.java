package SJU.SJUbaemin.Domain.Order;

import SJU.SJUbaemin.Domain.Member.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate date;
    private OrderStatus orderStatus;
}
