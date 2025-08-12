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

    //본인 계정 조회
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserFromSession(HttpSession session){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserFromSession(session));
    }

    //본인 계정 수정
    @PatchMapping("/modify")
    public ResponseEntity<UserResponseDto> modifyUser(HttpSession session,
                                                      @Validated @RequestBody UserModifyRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyUser(session, dto));
    }

    //본인 게정 삭제
    @DeleteMapping("/delete")
    public void deleteUser(HttpSession session){
        userService.deleteUser(session);
    }
    // TODO: 관리자용 메서드. DB에 관리자계정 세팅 후 사용한다는 가정

    @GetMapping("/manage/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
