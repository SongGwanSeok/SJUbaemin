//package SJU.SJUbaemin.Repository;
//
//import SJU.SJUbaemin.Domain.Board;
//import SJU.SJUbaemin.Domain.BoardRepository;
//import SJU.SJUbaemin.Domain.Member;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//@Transactional
//class BoardRepositoryTest {
//
//    @Autowired
//    BoardRepository boardRepository;
//    @Autowired MemberRepository memberRepository;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    @BeforeEach
//    public void 게시판_등록() throws Exception{
//        //given
//
//        Board board = Board.builder()
//                .title("1번 게시글")
//                .content("안녕하세요!!")
////                .member(memberRepository.findOne(1L))
//                .build();
//
//        //when
//        boardRepository.save(board);
//        //then
//        Board entity = boardRepository.findOne(board.getId());
//        assertThat(entity.getTitle()).isEqualTo("1번 게시글");
//        assertThat(entity.getContent()).isEqualTo("안녕하세요!!");
//    }
//
////    @Test
////    public void 게시판_수정() throws Exception{
////        //given
////        Board board = boardRepository.findOne(0L);
////        //when
////
////        //then
////
////    }
//}