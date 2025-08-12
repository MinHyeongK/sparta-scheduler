package min.scheduleproject.auth.repository;

import min.scheduleproject.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
