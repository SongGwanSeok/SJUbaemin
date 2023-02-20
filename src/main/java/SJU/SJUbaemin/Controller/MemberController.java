package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.MemberDto;
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
            @Valid @RequestBody MemberDto memberDto
            ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Member> getMyMemberInfo() {
        return ResponseEntity.ok(memberService.getMyMemberWithAuthorities().get());
    }

    @GetMapping("/{loginId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Member> getMemberInfo(@PathVariable String loginId) {
        return ResponseEntity.ok(memberService.getMemberWithAuthorities(loginId).get());
    }

    @DeleteMapping("/delete/{loginId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Long> deleteMember(@PathVariable String loginId) {
        return ResponseEntity.ok(memberService.delete(loginId));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MemberDto> updateMember( @PathVariable("id") Long id,
                                                   @RequestBody @Valid MemberDto memberDto) {
        memberService.update(id, memberDto);
        Member findMember = memberService.findByMemberId(id);
        return ResponseEntity.ok(MemberDto.builder()
                .loginId(findMember.getLoginId())
                .loginPw(findMember.getLoginPw())
                .name(findMember.getName())
                .email(findMember.getEmail())
                .birthday(findMember.getBirthday())
                .phone(findMember.getPhone())
                .address(findMember.getAddress())
                .build());
    }

}
