package min.scheduleproject.dto;

import min.scheduleproject.entity.Schedule;
import java.time.LocalDateTime;

public record ScheduleFindResponseDto(Long id, String title, String contents, String name, LocalDateTime createdAt,
                                      LocalDateTime modifiedAt) {
    public static ScheduleFindResponseDto from(Schedule s) {
        return new ScheduleFindResponseDto(s.getId(), s.getTitle(), s.getContents(), s.getName(), s.getCreatedAt(), s.getModifiedAt());
    }
}
