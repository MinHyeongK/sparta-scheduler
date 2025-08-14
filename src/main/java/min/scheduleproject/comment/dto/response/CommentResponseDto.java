package min.scheduleproject.comment.dto.response;

import min.scheduleproject.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long commentId,
        String contents,
        Long userId,
        Long scheduleId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
        ){

        public static CommentResponseDto of(Comment comment){
                return new CommentResponseDto(
                        comment.getCommentId(),
                        comment.getContents(),
                        comment.getUser().getUid(),
                        comment.getSchedule().getScheduleId(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt());
        }
}
