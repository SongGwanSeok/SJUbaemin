package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Member.MemberRequestDto;
import SJU.SJUbaemin.Domain.Dto.Member.MemberResponseDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(
            @Valid @RequestBody MemberRequestDto memberRequestDto
            ) {

        return ResponseEntity.ok(memberService.signup(memberRequestDto));
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
        Member member = memberService.getMyMemberWithAuthorities().get();

        return ResponseEntity.ok(memberService.memberEntityToDto(member));
    }

    @GetMapping("/{loginId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberResponseDto> getMemberInfo(@PathVariable String loginId) {
        Member member = memberService.getMemberWithAuthorities(loginId).get();

        return ResponseEntity.ok(memberService.memberEntityToDto(member));
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
        Member findById = memberService.findByMemberId(id);

        return ResponseEntity.ok(memberService.memberEntityToDto(findById));
    }

}
