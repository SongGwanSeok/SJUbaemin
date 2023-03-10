package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Board.BoardResponseDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardSaveRequestDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardUpdateRequestDto;
import SJU.SJUbaemin.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/save/{memberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BoardResponseDto> save(
            @PathVariable Long memberId,
            @RequestBody BoardSaveRequestDto requestDto) {
        return ResponseEntity.ok(boardService.save(memberId, requestDto));
    }
    @PutMapping("/update/{boardId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto boardRequestDto) {
        boardService.update(boardId, boardRequestDto);
        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BoardResponseDto>> findAll() {


        return ResponseEntity.ok(boardService.findAllDesc());
    }
    @GetMapping("/findAll/member")
    public ResponseEntity<List<BoardResponseDto>> findAllWithMember() {

        return ResponseEntity.ok(boardService.findWithMember());
    }

    @GetMapping("/findById/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BoardResponseDto>> search(@RequestParam(value = "keyword") String keyword){

        List<BoardResponseDto> searchList = boardService.search(keyword);

        return ResponseEntity.ok(searchList);
    }

}
