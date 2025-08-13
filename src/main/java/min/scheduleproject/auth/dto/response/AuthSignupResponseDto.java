package min.scheduleproject.auth.dto.response;

import min.scheduleproject.user.entity.User;

import java.time.LocalDateTime;

public record AuthSignupResponseDto(
        Long id,
        String userName,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt){

    public static AuthSignupResponseDto from(User userEntity) {
    return new AuthSignupResponseDto(
            userEntity.getUid(),
            userEntity.getUserName(),
            userEntity.getEmail(),
            userEntity.getCreatedAt(),
            userEntity.getModifiedAt());
    }
}
