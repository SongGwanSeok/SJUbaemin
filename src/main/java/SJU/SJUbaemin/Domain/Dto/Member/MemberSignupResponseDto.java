package SJU.SJUbaemin.Domain.Dto.Member;

import SJU.SJUbaemin.Domain.Authority;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupResponseDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String loginId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String loginPw;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private String email;
    private String birthday;
    private String phone;
    private String address;



    private Boolean admin;

//    public static MemberDto from(Member user) {
//        if(user == null) return null;
//
//        return MemberDto.builder()
//                .loginId(user.getLoginId())
//                .username(user.getUsername())
//                .authorityDtoSet(user.getAuthorities().stream()
//                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
//                        .collect(Collectors.toSet()))
//                .build();
//    }
}
