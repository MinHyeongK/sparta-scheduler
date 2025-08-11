package min.scheduleproject.schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.schedule.dto.*;
import min.scheduleproject.schedule.entity.Schedule;
import min.scheduleproject.schedule.repository.ScheduleRepository;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto) {

        User user = userRepository.findById(dto.getUid()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저"));
        //Schedule schedule1 = ScheduleMapper.INSTANCE.of(user, dto);

        Schedule schedule2 = Schedule.of(user, dto);

        //log.info("ScheduleMapper: " + schedule1.toString());

        //Schedule created = scheduleRepository.save(schedule1);
        Schedule created = scheduleRepository.save(schedule2);

        return ScheduleResponseDto.from(created);
    }

    @Transactional(readOnly = true)
    public ScheduleGetResponseDto findSchedule(Long id) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));
        return ScheduleGetResponseDto.from(found);
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetResponseDto> findAllSchedules(Long uid) {
        List<Schedule> foundSchedules = scheduleRepository.findAllByUserIdOrderByModifiedAtDesc(uid);
        List<ScheduleGetResponseDto> dtos = foundSchedules.stream()
                .map(ScheduleGetResponseDto::from)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public ScheduleResponseDto modifySchedule(long id, ScheduleModifyRequestDto dto) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(dto.uid(), found.getUser().getId())) throw new IllegalStateException("유저 아이디 불일치");
        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        found.modifySchedule(dto.title());
        return ScheduleResponseDto.from(found);
    }

    @Transactional
    public void deleteSchedule(long id, ScheduleDeleteRequestDto dto) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(dto.uid(), found.getUser().getId())) throw new IllegalStateException("유저 아이디 불일치");
        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        scheduleRepository.deleteById(id);
    }
}
