package SJU.SJUbaemin.Domain.Dto.Board;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {

    private Long id;
    private String title;
    private Member member;
    private LocalDateTime upt_date;

    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title=entity.getTitle();
        this.member=getMember();
        this.upt_date = entity.getUpt_date();
    }
}
