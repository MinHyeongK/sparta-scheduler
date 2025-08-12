package min.scheduleproject.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleCreateRequestDto  (
        @NotBlank @Size(min = 1, max = 30) String title,
        @NotBlank @Size(min = 1, max = 200)String contents){
}
