package SJU.SJUbaemin.Domain.Entity.Board;

import SJU.SJUbaemin.Domain.Entity.Member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne //ManyToOne은 기본타입이 Eager이므로 변경
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;
    private Long view_cnt;   //조회수
    private Long comment_cnt;

    @CreatedDate
    private LocalDateTime reg_date; //등록 날짜
    @LastModifiedDate
    private LocalDateTime upt_date; //수정 날짜
    private String comment;

    @Builder
    public Board(String title, String content, Member member, Long view_cnt) {
        this.title = title;
        this.content = content;
        this.view_cnt = 0L;
        this.member = member;
    }


    public void update(String title, String content) {
        this.title=title;
        this.content=content;
//        this.upt_date= LocalDateTime.now();
    }
}
