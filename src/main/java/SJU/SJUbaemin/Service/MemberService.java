package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Entity.Member.Authority;
import SJU.SJUbaemin.Domain.Dto.Member.MemberRequestDto;
import SJU.SJUbaemin.Domain.Dto.Member.MemberResponseDto;
import SJU.SJUbaemin.Domain.Entity.Member.Member;
import SJU.SJUbaemin.Repository.MemberRepository;
import SJU.SJUbaemin.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    /**
     * 권한 정보 포함 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if(memberRepository.findOneWithAuthoritiesByLoginId(memberRequestDto.getLoginId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Member member = memberDtoToEntity(memberRequestDto);

        return memberEntityToDto(memberRepository.save(member));
    }

    /**
     * loginId를 사용해 어떠한 Member 객체든 권한정보를 가져온다.
     */

    @Transactional(readOnly = true) // loginId를 사용해 어떠한 Member 객체든 권한정보를 가져온다.
    public Optional<Member> getMemberWithAuthorities(String loginId) {
        return memberRepository.findOneWithAuthoritiesByLoginId(loginId);
    }
    //두 메서드의 허용 권한이 다르다.
    @Transactional(readOnly = true) // 현재 SecurityContext에 저장된 loginId에 해당하는 Member정보와 권한정보를 가져온다.
    public Optional<Member> getMyMemberWithAuthorities() {
        return SecurityUtil.getCurrentLoginId().flatMap(memberRepository::findOneWithAuthoritiesByLoginId);
    }

    /**
     * 고유 id로 찾기
     */
    @Transactional(readOnly = true)
    public Member findByMemberId(Long id) {
        Optional<Member> member = memberRepository.findById(id);


        return member.get();
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public Long delete(String loginId) {
        Optional<Member> member = memberRepository.findOneWithAuthoritiesByLoginId(loginId);
        memberRepository.delete(member.get());
        return member.orElseThrow().getId();
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public Long update(Long memberId, MemberRequestDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalArgumentException("찾을 수 없는 id입니다.");
        });


        if(!member.getLoginId().equals(memberDto.getLoginId())) {
            if(memberRepository.findOneWithAuthoritiesByLoginId(memberDto.getLoginId()).isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
            }
        }

        memberDto.setLoginPw(passwordEncoder.encode(memberDto.getLoginPw()));
        member.update(memberDto);
        return member.getId();
    }

    /**
     * memberEntityToDto, memberDtoToEntity 메서드
     */
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
