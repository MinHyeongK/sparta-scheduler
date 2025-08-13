package min.scheduleproject.comment.dto;

import min.scheduleproject.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        String contents,
        Long userId,
        Long scheduleId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
        ){

        public static CommentResponseDto of(Comment comment){
                return new CommentResponseDto(
                        comment.getContents(),
                        comment.getUser().getUid(),
                        comment.getSchedule().getScheduleId(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt());
        }
}
