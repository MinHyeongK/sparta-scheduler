package min.scheduleproject.user.dto.response;

import min.scheduleproject.user.entity.UserEntity;

import java.time.LocalDateTime;

public record UserResponseDto (Long id,
                               String userName,
                               String email,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedAt){
    public static UserResponseDto from(UserEntity user) {
    return new UserResponseDto(user.getUid(), user.getUserName(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }
}
