package SJU.SJUbaemin.Domain.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;
    private String email;
    private String birthday;
    private String phone;

    @Embedded
    private Address address;

    public Member(String name, String email) {
        this.userName = name;
        this.email = email;
    }
}
