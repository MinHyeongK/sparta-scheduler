package min.scheduleproject.user.service;

import lombok.RequiredArgsConstructor;
import min.scheduleproject.user.dto.UserModifyRequestDto;
import min.scheduleproject.user.dto.UserRequestDto;
import min.scheduleproject.user.dto.UserResponseDto;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto dto){

        User created = userRepository.save(User.of(dto.getUserName(), dto.getEmail(), dto.getPassword()));
        return UserResponseDto.from(created);
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

    public void deleteUser(Long id, String password) {
        User found = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(found.getPassword(), password)) throw new IllegalStateException("비밀번호 불일치");

        userRepository.deleteById(id);
    }
}
