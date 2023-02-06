package SJU.SJUbaemin.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();


}
