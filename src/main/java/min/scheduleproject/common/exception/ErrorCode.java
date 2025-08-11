package min.scheduleproject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_LIMIT_EXCEEDED("USR-003", "가입 가능한 사용자 수를 초과했습니다."),
    DUPLICATE_NAME("USR-001", "이미 회원가입된 이름입니다."),
    INVALID_NAME("VAL-001", "이름은 2글자 이상이어야 합니다.");

    private final String code;
    private final String message;
}
