package min.scheduleproject.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.auth.dto.request.AuthLoginRequestDto;
import min.scheduleproject.auth.dto.request.AuthSignupRequestDto;
import min.scheduleproject.auth.dto.response.AuthSignupResponseDto;
import min.scheduleproject.auth.repository.AuthRepository;
import min.scheduleproject.common.config.PasswordEncoder;
import min.scheduleproject.common.exception.CustomException;
import min.scheduleproject.common.exception.ErrorCode;
import min.scheduleproject.user.entity.UserEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthSignupResponseDto signup(AuthSignupRequestDto dto){
        UserEntity userEntity = authRepository.findByEmail(dto.email());
        if (userEntity != null) throw new CustomException(ErrorCode.DUPLICATE_NAME);

        String encodedPassword = passwordEncoder.encode(dto.password());

        UserEntity created = authRepository.save(new UserEntity(dto.userName(), dto.email(), encodedPassword));

        return AuthSignupResponseDto.from(created);
    }

    public void login(HttpServletRequest request, AuthLoginRequestDto dto) {
        UserEntity userEntity = authRepository.findByEmail(dto.email());

        if(!passwordEncoder.matches(dto.password(), userEntity.getPassword())){
            throw new CustomException(ErrorCode.LOGIN_PASSWORD_MISMATCH);
        }

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", userEntity.getUid());
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }
}
