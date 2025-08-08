package min.scheduleproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String contents;
    private String password;

    private Schedule(String name, String title, String contents, String password) {
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public static Schedule of(String name, String title, String contents, String password){
        return new Schedule(name, title, contents, password);
    }

    public void modifyTitleName(String title, String name){
        this.title = title;
        this.name = name;
    }
}
