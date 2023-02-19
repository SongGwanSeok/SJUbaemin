package SJU.SJUbaemin.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // Board클래스로 DB에 접근하게 해줄 인터페이스
    // Entity 크랠스와 기본 Entity Repository가 함께 위치해야함

    @Query("SELECT b FROM Board b ORDER BY b.id DESC")  //내림차순 정렬?
    List<Board> findAllDesc();
}
