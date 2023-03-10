package SJU.SJUbaemin.Repository;

import SJU.SJUbaemin.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
