package SJU.SJUbaemin.Domain.Dto.Comment;

import SJU.SJUbaemin.Domain.Entity.Board.Board;
import SJU.SJUbaemin.Domain.Entity.Member.Member;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentRequestDto {

    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Member member;
    private Board boards;


}
