package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Authority;
import SJU.SJUbaemin.Domain.Dto.MemberDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Repository.MemberRepository;
import SJU.SJUbaemin.Repository.MemberRepositoryAuth;
import SJU.SJUbaemin.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepositoryAuth memberRepositoryAuth;

    /**
     * 회원가입
     */

    public Long join(Member member) {
        validationDuplicateMemberName(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //멤버 이름 중복 확인
    private void validationDuplicateMemberName(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if (!findMembers.isEmpty()) { // memberRepository 에서 이름으로 검색한게 결과가 있다면,
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 예외처리
        }
    }

    //멤버 아이디 중복 확인
    private void validationDuplicateMemberId(Member member) {

    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 id 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    /**
     * 권한 정보 포함 회원가입
     */
    @Transactional
    public Member signup(MemberDto memberDto) {
        if(memberRepositoryAuth.findOneWithAuthoritiesByLoginId(memberDto.getLoginId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .loginPw(passwordEncoder.encode(memberDto.getLoginPw()))
                .username(memberDto.getUsername())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return memberRepositoryAuth.save(member);
    }

    @Transactional(readOnly = true) // loginId를 사용해 어떠한 Member 객체든 권한정보를 가져온다.
    public Optional<Member> getMemberWithAuthorities(String loginId) {
        return memberRepositoryAuth.findOneWithAuthoritiesByLoginId(loginId);
    }
    //두 메서드의 허용 권한이 다르다.
    @Transactional(readOnly = true) // 현재 SecurityContext에 저장된 loginId에 해당하는 Member정보와 권한정보를 가져온다.
    public Optional<Member> getMyMemberWithAuthorities() {
        return SecurityUtil.getCurrentLoginId().flatMap(memberRepositoryAuth::findOneWithAuthoritiesByLoginId);
    }

}
