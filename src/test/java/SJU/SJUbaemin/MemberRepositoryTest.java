package SJU.SJUbaemin;

import SJU.SJUbaemin.Domain.Member.Member;
import SJU.SJUbaemin.Repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() {
        Member member = new Member("memberA", "sdfas");
        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());


    }

}
