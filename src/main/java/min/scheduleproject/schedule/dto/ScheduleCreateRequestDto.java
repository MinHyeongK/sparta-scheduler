package min.scheduleproject.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto  {
    private Long uid;
    private String title;
    private String contents;
    private String password;
}
