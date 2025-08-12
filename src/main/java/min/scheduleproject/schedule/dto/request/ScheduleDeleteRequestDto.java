package min.scheduleproject.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ScheduleDeleteRequestDto(@NotBlank Long uid) {
}
