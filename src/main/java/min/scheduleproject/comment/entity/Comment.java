package min.scheduleproject.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import min.scheduleproject.comment.dto.CommentRequestDto;
import min.scheduleproject.common.entity.BaseTimeEntity;
import min.scheduleproject.schedule.entity.Schedule;
import min.scheduleproject.user.entity.User;


@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    private String contents;

    public Comment (User user, Schedule schedule, String contents){
        this.user = user;
        this.schedule = schedule;
        this.contents = contents;
    }

    public static Comment from(User user, Schedule schedule, CommentRequestDto dto){
        return new Comment(user, schedule, dto.contents());
    }

    public void modifyComment(String contents){
        this.contents = contents;
    }
}
