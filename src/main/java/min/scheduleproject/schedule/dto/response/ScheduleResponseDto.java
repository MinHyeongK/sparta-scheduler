package min.scheduleproject.schedule.dto.response;

import min.scheduleproject.schedule.entity.Schedule;
import java.time.LocalDateTime;

// Create와 Modify 에서 사용
public record ScheduleResponseDto (Long scheduleId,
                                   String title,
                                   String contents,
                                   String name,
                                   LocalDateTime createdAt,
                                   LocalDateTime modifiedAt){
    public static ScheduleResponseDto from(Schedule s){
        return new ScheduleResponseDto(s.getScheduleId(), s.getTitle(), s.getContents(), s.getUser().getUserName(), s.getCreatedAt(), s.getModifiedAt());
    }
}
