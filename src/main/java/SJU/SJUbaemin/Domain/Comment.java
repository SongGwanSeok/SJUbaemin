package SJU.SJUbaemin.Domain;

import SJU.SJUbaemin.Domain.Entity.Board.Board;
import SJU.SJUbaemin.Domain.Entity.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @CreatedDate
    @Column(name = "reg_date",updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "upt_date")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board boards;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;


}
