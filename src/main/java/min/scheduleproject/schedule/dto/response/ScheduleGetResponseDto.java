package min.scheduleproject.schedule.dto.response;

import min.scheduleproject.comment.dto.response.CommentResponseDto;
import min.scheduleproject.schedule.entity.Schedule;
import java.time.LocalDateTime;
import java.util.List;

public record ScheduleGetResponseDto(Long scheduleId,
                                     String title,
                                     String contents,
                                     String name,
                                     List<CommentResponseDto> commentResponseDtos,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt) {
    public static ScheduleGetResponseDto from(Schedule s, List<CommentResponseDto> dtos) {
        return new ScheduleGetResponseDto(s.getScheduleId(), s.getTitle(), s.getContents(), s.getUser().getUserName(), dtos, s.getCreatedAt(), s.getModifiedAt());
    }
}
