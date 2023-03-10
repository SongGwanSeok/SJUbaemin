package SJU.SJUbaemin.Domain.Entity.Board;

import SJU.SJUbaemin.Domain.Comment;
import SJU.SJUbaemin.Domain.Entity.Member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board{

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne은 기본타입이 Eager이므로 변경
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "boards", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Column(length = 500, nullable = false)
    private String title;
    private String content;

    @Column(columnDefinition = "integer default 0")
    private Long view_cnt;   //조회수
    @CreatedDate
    @Column(name = "reg_date",updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "upt_date")
    private LocalDateTime modifiedDate;



    @Builder
    public Board(String title, String content, Member member,Long view_cnt, LocalDateTime createDate,LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.view_cnt = 0L;
        this.member = member;
        this.view_cnt = view_cnt;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
