package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Member.MemberSignupRequestDto;
import SJU.SJUbaemin.Domain.Dto.Member.MemberSignupResponseDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(
            @Valid @RequestBody MemberSignupRequestDto memberDto
            ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MemberSignupResponseDto> getMyMemberInfo() {
        Member member = memberService.getMyMemberWithAuthorities().get();
        return ResponseEntity.ok(memberEntityToDto(member));
    }

    @GetMapping("/{loginId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberSignupResponseDto> getMemberInfo(@PathVariable String loginId) {
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
    public ResponseEntity<MemberSignupResponseDto> updateMember(@PathVariable("id") Long id,
                                                                @RequestBody @Valid MemberSignupRequestDto memberDto) {
        memberService.update(id, memberDto);
        Member findMember = memberService.findByMemberId(id);
        return ResponseEntity.ok(memberEntityToDto(findMember));
    }

    public MemberSignupResponseDto memberEntityToDto(Member member) {

        return MemberSignupResponseDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .loginPw(member.getLoginPw())
                .name(member.getName())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
    }

}
