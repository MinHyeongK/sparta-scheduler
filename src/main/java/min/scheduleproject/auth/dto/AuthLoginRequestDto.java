package min.scheduleproject.auth.dto;

public record AuthLoginRequestDto(
        String email,
        String password)
{}