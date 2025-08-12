package min.scheduleproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.auth.dto.AuthLoginRequestDto;
import min.scheduleproject.auth.dto.AuthRequestDto;
import min.scheduleproject.auth.dto.AuthResponseDto;
import min.scheduleproject.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody AuthRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(dto));
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request,
                      @RequestBody AuthLoginRequestDto dto){
        authService.login(request, dto);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request){
        authService.logout(request);
    }
}
