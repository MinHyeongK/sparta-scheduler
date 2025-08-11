package min.scheduleproject.user.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.user.dto.UserLoginRequestDto;
import min.scheduleproject.user.dto.UserModifyRequestDto;
import min.scheduleproject.user.dto.UserRequestDto;
import min.scheduleproject.user.dto.UserResponseDto;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signup(UserRequestDto dto){
        User user = userRepository.findByEmail(dto.getEmail());
        if (user != null){
            throw new DuplicateKeyException("이미 존재하는 이메일");
        }
        User created = userRepository.save(User.of(dto));
        return UserResponseDto.from(created);
    }

    public void login(HttpServletRequest request, UserLoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.email());

        if(!ObjectUtils.nullSafeEquals(user.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", user.getId());
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));
        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::from).collect(Collectors.toList());
    }

    public UserResponseDto modifiedUser(Long id, UserModifyRequestDto dto) {
        User found = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.getPassword())) throw new IllegalStateException("비밀번호 불일치");

        found.modifyUser(dto.getUserName());
        return UserResponseDto.from(found);
    }

    public void deleteUser(Long id, UserRequestDto dto) {
        User found = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.getPassword())) throw new IllegalStateException("비밀번호 불일치");

        userRepository.deleteById(id);
    }
}
