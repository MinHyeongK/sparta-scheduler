package min.scheduleproject.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthSignupRequestDto(
        @NotBlank(message = "유저명을 입력해주세요.")
        @Size(min = 1, max = 10, message = "유저명은 최소 1자, 최대 10자입니다.")
        String userName,
        @NotBlank(message = "이메일을 입력해주세요.") @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 1, max = 20, message = "비밀번호는 최소 1자, 최대 20자입니다.")
        String password) {
}
