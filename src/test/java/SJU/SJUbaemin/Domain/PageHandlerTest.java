package SJU.SJUbaemin.Domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
class PageHandlerTest {

    @Test
    public void 현재_페이지_시작_끝_페이지() throws Exception{
        //given
        PageHandler ph = new PageHandler(250, 1);
        //when

        //then
        Assertions.assertEquals(ph.getBeginPage(), 1);
        Assertions.assertEquals(ph.getEndPage(), 10);
    }

    @Test
    public void 현재_페이지_시작_끝_페이지2() throws Exception{
        //given
        PageHandler ph = new PageHandler(255, 25);
        //when

        //then
        Assertions.assertEquals(ph.getBeginPage(), 21);
        Assertions.assertEquals(ph.getEndPage(), 26);
    }
}