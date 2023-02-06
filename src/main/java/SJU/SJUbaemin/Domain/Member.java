package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Address;
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

    private String name;
    private String email;
    private String birthday;
    private String phone;

    @Embedded
    private Address address;

    private String loginId;
    private String loginPw;

    public Member(String name, String email, String birthday, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }
}
