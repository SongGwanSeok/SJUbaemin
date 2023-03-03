package SJU.SJUbaemin.Service;

import SJU.SJUbaemin.Domain.Board;
import SJU.SJUbaemin.Domain.Dto.Board.BoardSaveRequestDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardResponseDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardUpdateRequestDto;
import SJU.SJUbaemin.Domain.Member;
import SJU.SJUbaemin.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    /**
     * 게시글 생성
     */
    @Transactional
    public BoardResponseDto save(Long memberId, BoardSaveRequestDto boardSaveRequestDto) {
        Member member = memberService.findByMemberId(memberId);
        Board savedBoard = boardRepository.save(boardDtoToEntity(boardSaveRequestDto, member));


        return boardEntityToDto(savedBoard);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
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


    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );



        return boardEntityToDto(board);
    }

    /**
     * 게시글 리스트 조회
     */
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllDesc() {
        return boardRepository.findAll().stream()
                .map(b -> boardEntityToDto(b)
                ).collect(Collectors.toList());
    }

    /**
     * EntityToDto, DtoToEntity 메서드
     */
    private static BoardResponseDto boardEntityToDto(Board board) {

        log.info("board.getMember : {}", board.getMember().getName());
        return BoardResponseDto.builder()
                .id(board.getId())
                .member(board.getMember())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    private static Board boardDtoToEntity(BoardSaveRequestDto boardSaveRequestDto, Member member) {

        return Board.builder()
                .member(member)
                .title(boardSaveRequestDto.getTitle())
                .content(boardSaveRequestDto.getContent())
                .build();
    }
}

