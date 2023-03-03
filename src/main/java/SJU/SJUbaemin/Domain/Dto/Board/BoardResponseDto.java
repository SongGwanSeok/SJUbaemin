package SJU.SJUbaemin.Domain.Dto.Board;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
