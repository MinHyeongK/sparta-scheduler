package min.scheduleproject.comment.repository;

import min.scheduleproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findBySchedule_ScheduleIdOrderByCreatedAtDesc(Long scheduleScheduleId);

    Comment findByCommentId(Long commentId);

    List<Comment> findAllBySchedule_ScheduleIdOrderByCreatedAtAsc(Long scheduleId);

    boolean existsBySchedule_ScheduleId(Long scheduleId);
}
