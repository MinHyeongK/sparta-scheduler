package min.scheduleproject.schedule.dto;

import min.scheduleproject.schedule.entity.Schedule;
import java.time.LocalDateTime;

public record ScheduleGetResponseDto(Long id, String title, String contents, String name, LocalDateTime createdAt,
                                     LocalDateTime modifiedAt) {
    public static ScheduleGetResponseDto from(Schedule s) {
        return new ScheduleGetResponseDto(s.getId(), s.getTitle(), s.getContents(), s.getUser().getUserName(), s.getCreatedAt(), s.getModifiedAt());
    }
}
