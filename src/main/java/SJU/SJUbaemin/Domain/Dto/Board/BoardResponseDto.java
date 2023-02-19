package SJU.SJUbaemin.Domain.Dto.Board;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id;    //PK
    private Member member;  //멤버, 작성자 조회
    private String title;   //제목
    private String content; //내용
    private Long view_cnt;   //조회수
    private Long comment_cnt;   //댓글수
    private LocalDateTime reg_date; //등록 날짜
    private LocalDateTime upt_date; //수정 날짜
    private String comment; //댓글

    public BoardResponseDto(Board entity) {
        this.id=entity.getId();
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.member=entity.getMember();
        this.view_cnt = entity.getView_cnt();
        this.comment_cnt = entity.getComment_cnt();
        this.reg_date = entity.getReg_date();
        this.upt_date = entity.getUpt_date();
        this.comment = entity.getComment();
    }
}
