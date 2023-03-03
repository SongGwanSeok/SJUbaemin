package SJU.SJUbaemin.Domain.Dto.Member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String loginId;

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
