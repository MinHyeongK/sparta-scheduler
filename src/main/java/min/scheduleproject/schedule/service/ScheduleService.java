package min.scheduleproject.schedule.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.schedule.dto.request.ScheduleCreateRequestDto;
import min.scheduleproject.schedule.dto.request.ScheduleModifyRequestDto;
import min.scheduleproject.schedule.dto.response.ScheduleGetResponseDto;
import min.scheduleproject.schedule.dto.response.ScheduleResponseDto;
import min.scheduleproject.schedule.entity.Schedule;
import min.scheduleproject.schedule.repository.ScheduleRepository;
import min.scheduleproject.user.entity.UserEntity;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(HttpSession session,
                                              ScheduleCreateRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        UserEntity user = userRepository.findById(uid).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저"));

        Schedule schedule = Schedule.of(user, dto);
        Schedule created = scheduleRepository.save(schedule);

        return ScheduleResponseDto.from(created);
    }

    //TODO: shceudleId로 일정을 반환하는 메서드 제작
    public ScheduleGetResponseDto findScheduleByScheduleId(long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 scheduleId"));

        return ScheduleGetResponseDto.from(found);
    }

    // TODO: 동일 title의 일정을 list형태로 반환
    public List<ScheduleGetResponseDto> findScheduleByTitle(String title) {
        List<Schedule> foundSchedules = scheduleRepository.findAllByTitleOrderByModifiedAtDesc(title);
        List<ScheduleGetResponseDto> dtos = foundSchedules.stream()
                .map(ScheduleGetResponseDto::from)
                .collect(Collectors.toList());

        return dtos;
    }

    public List<ScheduleGetResponseDto> findAllSchedulesByUid(Long uid) {
        //QUESTION: 위 코드와 튜터님이 새로 추천해주신 방법 중 뭐가 더 좋을까
        Sort sort = Sort.by("createdAt").descending();

        List<Schedule> foundSchedules = scheduleRepository.findAllByUserUid(uid, sort);
        List<ScheduleGetResponseDto> dtos = foundSchedules.stream()
                .map(ScheduleGetResponseDto::from)
                .collect(Collectors.toList());

        return dtos;
    }

    @Transactional
    public ScheduleResponseDto modifySchedule(HttpSession session, Long scheduleId, ScheduleModifyRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(uid, found.getUser().getUid())) throw new IllegalStateException("유저 아이디 불일치");

        found.modifySchedule(dto.title(), dto.contents());

        return ScheduleResponseDto.from(found);
    }

    @Transactional
    public void deleteSchedule(HttpSession session, Long scheduleId) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(uid, found.getUser().getUid())) throw new IllegalStateException("유저 아이디 불일치");

        scheduleRepository.deleteById(scheduleId);
    }


}
