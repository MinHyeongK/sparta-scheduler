package min.scheduleproject.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDeleteRequestDto (
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 1, max = 20, message = "비밀번호는 최소 1자, 최대 20자입니다.")
        String password
){
}
