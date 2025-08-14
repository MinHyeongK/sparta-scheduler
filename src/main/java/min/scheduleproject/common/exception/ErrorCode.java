package min.scheduleproject.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATE_NAME("USR-001", "이미 회원가입된 이메일입니다."),
    USER_NOT_FOUND("USR-002", "존재하지 않는 사용자 ID입니다."),
    USER_ID_MISMATCH("USR-003", "유저 ID가 일치하지 않습니다."),

    INVALID_NAME("VAL-001", "이름은 2글자 이상, 10글자 이하이어야 합니다."),
    INVALID_TITLE("VAL-002", "제목은 1글자 이상, 10글자 이하이어야 합니다."),
    INVALID_CONTENTS("VAL-003", "내용은 1글자 이상, 200글자 이하이어야 합니다."),

    LOGIN_PASSWORD_MISMATCH("AUTH-001", "비밀번호가 일치하지 않습니다."),
    LOGIN_USER_NOT_FOUND("AUTH-002", "해당 이메일의 사용자가 존재하지 않습니다."),
    INVALID_PASSWORD_FORMAT("AUTH-003", "비밀번호는 8자 이상, 20자 이하이어야 합니다."),

    SCHEDULE_NOT_FOUND("SCH-001", "존재하지 않는 일정 ID입니다."),
    SCHEDULE_HAS_COMMENTS("SCH-002", "댓글이 있는 일정은 삭제할 수 없습니다.");

    private final String code;
    private final String message;
}
