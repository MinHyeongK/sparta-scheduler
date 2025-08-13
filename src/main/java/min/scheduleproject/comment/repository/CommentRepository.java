package min.scheduleproject.comment.repository;

import min.scheduleproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findBySchedule_ScheduleIdOrderByCreatedAtDesc(Long scheduleScheduleId);

    Comment findByCommentId(Long commentId);
}
