package min.scheduleproject.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import min.scheduleproject.common.entity.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long uid;
    @Column(unique = true, name = "user_name", nullable = false, length = 10)
    private String userName;
    @Column(unique = true, name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void modifyUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    };
}
