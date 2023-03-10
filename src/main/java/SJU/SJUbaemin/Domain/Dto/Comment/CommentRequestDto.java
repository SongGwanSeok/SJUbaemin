package SJU.SJUbaemin.Domain.Dto.Comment;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Member;
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
