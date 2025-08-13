package min.scheduleproject.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import min.scheduleproject.common.entity.BaseTimeEntity;
import min.scheduleproject.schedule.dto.request.ScheduleCreateRequestDto;
import min.scheduleproject.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "title", nullable = false, length = 10)
    private String title;
    @Column(name = "contents", nullable = false, length = 200)
    private String contents;

    private Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public static Schedule from(User user, ScheduleCreateRequestDto dto){
        return new Schedule(user, dto.title(), dto.contents());
    }

    public void modifySchedule(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
