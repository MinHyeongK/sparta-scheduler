package min.scheduleproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.user.dto.UserLoginRequestDto;
import min.scheduleproject.user.dto.UserModifyRequestDto;
import min.scheduleproject.user.dto.UserRequestDto;
import min.scheduleproject.user.dto.UserResponseDto;
import min.scheduleproject.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(dto));
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request,
                      @RequestBody UserLoginRequestDto dto){
        userService.login(request, dto);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request){
        userService.logout(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> modifiedUser(@PathVariable long id, @RequestBody UserModifyRequestDto dto){

        return ResponseEntity.status(HttpStatus.OK).body(userService.modifiedUser(id, dto));
    }

    @DeleteMapping("/delete")
    public void deleteUser(@PathVariable long id,
                           @RequestParam UserRequestDto dto){
        userService.deleteUser(id, dto);
    }

}
