package min.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto  {
    private String title;
    private String contents;
    private String name;
    private String password;
}
