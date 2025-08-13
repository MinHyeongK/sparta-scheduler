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
    private Long uid;
    private String userName;
    @Column(unique = true)
    private String email;
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
