package min.scheduleproject.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import min.scheduleproject.common.entity.BaseTimeEntity;
import min.scheduleproject.user.dto.UserRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static User of(UserRequestDto dto) {
        return new User(dto.getUserName(), dto.getEmail(), dto.getPassword());
    }

    public void modifyUser(String userName){this.userName = userName;};
}
