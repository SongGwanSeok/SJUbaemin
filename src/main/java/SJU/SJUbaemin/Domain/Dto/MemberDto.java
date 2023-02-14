package SJU.SJUbaemin.Domain.Dto;

import SJU.SJUbaemin.Domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String loginId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String loginPw;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    private String email;
    private String birthday;
    private String phone;
    private String address;



//    private Set<AuthorityDto> authorityDtoSet;
//
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
