package min.scheduleproject.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.auth.dto.request.AuthLoginRequestDto;
import min.scheduleproject.auth.dto.request.AuthSignupRequestDto;
import min.scheduleproject.auth.dto.response.AuthSignupResponseDto;
import min.scheduleproject.auth.repository.AuthRepository;
import min.scheduleproject.user.entity.UserEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public AuthSignupResponseDto signup(AuthSignupRequestDto dto){
        UserEntity userEntity = authRepository.findByEmail(dto.email());
        if (userEntity != null) throw new DuplicateKeyException("이미 존재하는 이메일");

        UserEntity created = authRepository.save(new UserEntity(dto.userName(), dto.email(), dto.password()));

        return AuthSignupResponseDto.from(created);
    }

    public void login(HttpServletRequest request, AuthLoginRequestDto dto) {
        UserEntity userEntity = authRepository.findByEmail(dto.email());

        if(!ObjectUtils.nullSafeEquals(userEntity.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", userEntity.getUid());
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }
}
