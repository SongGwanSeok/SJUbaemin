package SJU.SJUbaemin.Domain.Dto.Board;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSaveRequestDto {

    private String title;
    private String content;
    private Member member;

    @Builder
    public BoardSaveRequestDto(String title, String content, Member member) {
        this.title = title;
        this.content=content;
        this.member=member;
    }

    public Board toEntity(Member member) {
        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .view_cnt(0L)
                .build();
    }

}
