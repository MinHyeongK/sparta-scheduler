package min.scheduleproject.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleModifyRequestDto(
        @NotBlank(message = "제목을 입력해주세요.") @Size(min = 1, max = 30, message = "제목은 최소 1자, 최대 30자입니다.")
        String title,
        @NotBlank(message = "내용을 입력해주세요.") @Size(min = 1, max = 200, message = "제목은 최소 1자, 최대 200자입니다.")
        String contents) {
}
