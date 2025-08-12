package min.scheduleproject.auth.dto;

import min.scheduleproject.user.entity.UserEntity;

import java.time.LocalDateTime;

public record AuthResponseDto(
        Long id,
        String userName,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt){

    public static AuthResponseDto from(UserEntity userEntity) {
    return new AuthResponseDto(
            userEntity.getUid(),
            userEntity.getUserName(),
            userEntity.getEmail(),
            userEntity.getCreatedAt(),
            userEntity.getModifiedAt());
    }
}
