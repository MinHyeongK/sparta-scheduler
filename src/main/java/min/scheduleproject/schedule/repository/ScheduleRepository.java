package min.scheduleproject.schedule.repository;

import min.scheduleproject.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findAllByTitleOrderByModifiedAtDesc(Pageable pageable, String title);
    Page<Schedule> findAllByUserUidOrderByModifiedAtDesc(Pageable pageable, Long userUid);
}
