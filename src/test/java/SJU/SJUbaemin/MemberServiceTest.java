package SJU.SJUbaemin;

import SJU.SJUbaemin.Domain.Address;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Repository.MemberRepository;
import SJU.SJUbaemin.Service.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
//    @Rollback(value = false)
    public void 회원가입() throws  Exception {
        //given
        Address address = new Address("서울시", "능동로", "209");
        Member member = new Member("A", "email@sju.ac.kr", "002020", "010-1234-1234", address);

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class) // 이 예외가 발생하면 성공
    public void 중복회원() throws Exception {
        //given
        Address address1 = new Address("성남시", "둔촌대로", "171-6");
        Address address2 = new Address("서울시", "능동로", "209");

        Member member1 = new Member("A", "email@sju.ac.kr", "980902", "010-8552-6745", address1);
        Member member2 = new Member("A", "20003204@sju.ac.kr", "980905", "010-8784-7515", address2);

        //when
        Long member1Id = memberService.join(member1);
        Long member2Id = memberService.join(member2); //여기서 예외 발생

        //then
        Assert.fail("예외가 발생해야 한다.");


    }

}
