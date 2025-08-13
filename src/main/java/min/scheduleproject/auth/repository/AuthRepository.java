package min.scheduleproject.auth.repository;

import min.scheduleproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
