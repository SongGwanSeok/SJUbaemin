package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Dto.Member.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;
    @Column(name = "login_pw")
    private String loginPw;
    @Column(name = "name")
    private String name;

    private String email;
    private String birthday;
    private String phone;
    private String address;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

    public void update(MemberDto memberDto) {
        this.loginId = memberDto.getLoginId();
        this.loginPw = memberDto.getLoginPw();
        this.name = memberDto.getName();
        this.email = memberDto.getEmail();
        this.birthday = memberDto.getBirthday();
        this.phone = memberDto.getPhone();
        this.address = memberDto.getAddress();
    }




}
