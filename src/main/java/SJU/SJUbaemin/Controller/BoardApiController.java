package SJU.SJUbaemin.Controller;

import SJU.SJUbaemin.Domain.Dto.Board.BoardResponseDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardSaveRequestDto;
import SJU.SJUbaemin.Domain.Dto.Board.BoardUpdateRequestDto;
import SJU.SJUbaemin.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/save/{memberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Long save(
            @PathVariable Long memberId,
            @RequestBody BoardSaveRequestDto requestDto) {
        return boardService.save(memberId, requestDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }

    @GetMapping("/findById/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }
}
