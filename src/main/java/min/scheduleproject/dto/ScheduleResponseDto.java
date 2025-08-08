package min.scheduleproject.dto;

import min.scheduleproject.entity.Schedule;
import java.time.LocalDateTime;

// Create와 Modify 에서 사용
public record ScheduleResponseDto (Long id, String title, String contents, String name, LocalDateTime createdAt, LocalDateTime modifiedAt){
    public static ScheduleResponseDto from(Schedule s){
        return new ScheduleResponseDto(s.getId(), s.getTitle(), s.getContents(), s.getName(), s.getCreatedAt(), s.getModifiedAt());
    }
}
