package min.scheduleproject.user.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.user.dto.request.UserModifyRequestDto;
import min.scheduleproject.user.dto.response.UserResponseDto;
import min.scheduleproject.user.entity.UserEntity;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUserFromSession(HttpSession session) {
        //session 내부 보는 코드
        //Enumeration<String> attrNames = session.getAttributeNames();
        //while (attrNames.hasMoreElements()) {
        //    String name = attrNames.nextElement();
        //    Object value = session.getAttribute(name);
        //    log.info("Session attribute [{}] = {}", name, value);
        //}
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        UserEntity user = userRepository.findById(uid).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto modifyUser(HttpSession session, UserModifyRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        UserEntity found = userRepository.findById(uid).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        found.modifyUser(dto.userName(), dto.password());

        return UserResponseDto.from(found);
    }

    @Transactional
    public void deleteUser(HttpSession session) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        UserEntity found = userRepository.findById(uid).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        userRepository.deleteById(uid);
    }

    //TODO: UserController 확인

    public UserResponseDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(UserResponseDto::from).collect(Collectors.toList());
    }
}
