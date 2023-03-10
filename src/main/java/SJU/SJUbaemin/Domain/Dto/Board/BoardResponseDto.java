package SJU.SJUbaemin.Domain.Dto.Board;

import SJU.SJUbaemin.Domain.Entity.Board.Board;
import SJU.SJUbaemin.Domain.Entity.Member.Member;
import SJU.SJUbaemin.Domain.Dto.Comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto{

    private Long id;    //PK
    private Member member;  //멤버, 작성자 조회
    private String title;   //제목
    private String content; //내용
    private Long view_cnt;   //조회수
    private List<CommentResponseDto> comments;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board b) {
        id = b.getId();
        member = b.getMember();
        title = b.getTitle();
        content = b.getContent();
    }
}
