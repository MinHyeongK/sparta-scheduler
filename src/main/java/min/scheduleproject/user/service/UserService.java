package min.scheduleproject.user.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.common.exception.CustomException;
import min.scheduleproject.common.exception.ErrorCode;
import min.scheduleproject.user.dto.request.UserModifyRequestDto;
import min.scheduleproject.user.dto.response.UserResponseDto;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getCurrentUser(HttpSession session) {
        //session 내부 보는 코드
        Enumeration<String> attrNames = session.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            Object value = session.getAttribute(name);
            log.info("Session attribute [{}] = {}", name, value);
        }
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        User user = userRepository.findById(uid).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto modifyCurrentUser(HttpSession session, UserModifyRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        User found = userRepository.findById(uid).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        found.modifyUser(dto.userName(), dto.password());

        return UserResponseDto.from(found);
    }

    @Transactional
    public void deleteCurrentUser(HttpSession session) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        User found = userRepository.findById(uid).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.deleteById(uid);
    }

    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserResponseDto::from).collect(Collectors.toList());
    }
}
