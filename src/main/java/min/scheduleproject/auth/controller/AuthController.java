package min.scheduleproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.auth.dto.request.AuthLoginRequestDto;
import min.scheduleproject.auth.dto.request.AuthSignupRequestDto;
import min.scheduleproject.auth.dto.response.AuthSignupResponseDto;
import min.scheduleproject.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthSignupResponseDto> signup(@Validated  @RequestBody AuthSignupRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(dto));
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request,
                      @RequestBody AuthLoginRequestDto dto){
        authService.login(request, dto);
    }

    @PostMapping("logout")
    public void logout(HttpSession session){
        authService.logout(session);
    }
}
