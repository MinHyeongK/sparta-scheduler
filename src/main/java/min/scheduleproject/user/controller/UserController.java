package min.scheduleproject.user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.user.dto.request.UserModifyRequestDto;
import min.scheduleproject.user.dto.response.UserResponseDto;
import min.scheduleproject.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(HttpSession session){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser(session));
    }

    @PatchMapping("/modify")
    public ResponseEntity<UserResponseDto> modifyCurrentUser(HttpSession session,
                                                             @Validated @RequestBody UserModifyRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyCurrentUser(session, dto));
    }

    @DeleteMapping("/delete")
    public void deleteCurrentUser(HttpSession session){
        userService.deleteCurrentUser(session);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
