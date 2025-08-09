package min.scheduleproject.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import min.scheduleproject.common.dto.BaseTimeEntity;
import min.scheduleproject.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    private String contents;
    private String title;
    private String password;

    private Schedule(User user, String title, String contents, String password) {
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public static Schedule of(User user, String title, String contents, String password){
        return new Schedule(user, title, contents, password);
    }

    public void modifySchedule(String title){
        this.title = title;
    }
}
