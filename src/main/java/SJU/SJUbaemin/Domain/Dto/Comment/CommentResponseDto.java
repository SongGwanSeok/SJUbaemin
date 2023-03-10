package SJU.SJUbaemin.Domain.Dto.Comment;

import SJU.SJUbaemin.Domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String nickname;
    private Long boardsId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreateDate();
        this.modifiedDate = comment.getModifiedDate();
        this.nickname = comment.getMember().getName();
        this.boardsId = comment.getBoards().getId();
    }

}
