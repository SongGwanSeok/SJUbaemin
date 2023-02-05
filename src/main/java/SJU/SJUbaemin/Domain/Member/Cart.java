package SJU.SJUbaemin.Domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private Long quantity;
    private int price;

}
