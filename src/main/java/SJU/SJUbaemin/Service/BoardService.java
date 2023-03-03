package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Dto.Board.BoardListResponseDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardSaveRequestDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardResponseDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardUpdateRequestDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Repository.BoardRepository;
import SJU.SJUbaemin.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 게시글 생성
     */
    @Transactional
    public Board save(Long memberId, BoardSaveRequestDto boardSaveRequestDto) {
        Member member = memberService.findByMemberId(memberId);
        return boardRepository.save(boardSaveRequestDto.toEntity(member));
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return board.getId();
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
                );
        boardRepository.delete(board);
    }

    public BoardResponseDto findById(Long id) {
        Board entity = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );
        return new BoardResponseDto(entity);
    }

    /**
     * 게시글 리스트 조회
     */
    @Transactional(readOnly = true)
    public List<BoardListResponseDto> findAllDesc() {
        return boardRepository.findAllDesc().stream()
                .map(BoardListResponseDto::new) // 왼쪽객체의 오른쪽 메서드를 사용한다.
                .collect(Collectors.toList());
    }

}

