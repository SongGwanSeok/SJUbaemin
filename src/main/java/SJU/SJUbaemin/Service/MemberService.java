package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
        List<Member> findMembers = memberRepository.findByName(member.getName());
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

}
