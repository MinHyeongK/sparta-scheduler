package min.scheduleproject.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequestDto(
        @NotBlank(message = "내용을 입력해주세요.")
        @Size(min = 1, max = 200, message = "내용은 최소 1자, 최대 200자입니다.")
        String contents){
}