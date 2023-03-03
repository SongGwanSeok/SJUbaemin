package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Authority;
import SJU.SJUbaemin.Domain.Dto.Member.MemberRequestDto;
import SJU.SJUbaemin.Domain.Dto.Member.MemberResponseDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(
            @Valid @RequestBody MemberRequestDto memberRequestDto
            ) {
        Member member = memberDtoToEntity(memberRequestDto);

        return ResponseEntity.ok(memberEntityToDto(memberService.signup(member)));
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        Member member = memberService.getMyMemberWithAuthorities().get();
        return ResponseEntity.ok(memberEntityToDto(member));
    }

    @GetMapping("/{loginId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String loginId) {
        Member member = memberService.getMemberWithAuthorities(loginId).get();

        return ResponseEntity.ok(memberEntityToDto(member));
    }

    @DeleteMapping("/delete/{loginId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Long> deleteMember(@PathVariable String loginId) {
        return ResponseEntity.ok(memberService.delete(loginId));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable("id") Long id,
                                                          @RequestBody @Valid MemberRequestDto memberDto) {
        memberService.update(id, memberDto);
        return ResponseEntity.ok(memberEntityToDto(memberService.findByMemberId(id)));
    }

    public MemberResponseDto memberEntityToDto(Member member) {

        Boolean admin = false;
        Set<Authority> authorities = member.getAuthorities();

        for (Authority authority : authorities) {
            if (authority.getAuthorityName().equals("ROLE_ADMIN")) {
                admin = true;
            }
        }

        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .phone(member.getPhone())
                .address(member.getAddress())
                .admin(admin)
                .build();

        return memberResponseDto;
    }


    private Member memberDtoToEntity(MemberRequestDto memberDto) {
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return Member.builder()
                .loginId(memberDto.getLoginId())
                .loginPw(passwordEncoder.encode(memberDto.getLoginPw()))
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .birthday(memberDto.getBirthday())
                .phone(memberDto.getPhone())
                .address(memberDto.getAddress())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
    }

}
