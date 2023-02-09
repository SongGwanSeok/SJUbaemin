package SJU.SJUbaemin.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

/**
 *  사용하지 않을까 생각중
 */
@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType type;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products = new ArrayList<>();

    public Category(ProductType type) {
        this.type = type;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>(); // 이 부분 이해 안감

    public void setParent(Category parent) {
        this.parent = parent;
    }

    //==연관관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
