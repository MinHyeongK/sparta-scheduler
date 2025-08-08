package min.scheduleproject.service;

import lombok.RequiredArgsConstructor;
import min.scheduleproject.dto.*;
import min.scheduleproject.entity.Schedule;
import min.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto) {
        Schedule created = scheduleRepository.save(Schedule.of(dto.getName(), dto.getTitle(), dto.getContents(), dto.getPassword()));
        return ScheduleResponseDto.from(created);
    }

    @Transactional(readOnly = true)
    public ScheduleFindResponseDto findSchedule(Long id) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));
        return ScheduleFindResponseDto.from(found);
    }

    @Transactional(readOnly = true)
    public List<ScheduleFindResponseDto> findAllSchedules(String name) {
        List<Schedule> foundSchedules = scheduleRepository.findAllByNameOrderByModifiedAtDesc(name);
        List<ScheduleFindResponseDto> dtos = foundSchedules.stream()
                .map(ScheduleFindResponseDto::from)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public ScheduleResponseDto modifySchedule(long id, ScheduleModifyRequestDto dto) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        found.modifyTitleName(dto.title(), dto.name());
        return ScheduleResponseDto.from(found);
    }

    @Transactional
    public void deleteSchedule(long id, ScheduleDeleteRequestDto dto) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 ID값"));

        if(!ObjectUtils.nullSafeEquals(found.getPassword(), dto.password())) throw new IllegalStateException("비밀번호 불일치");

        scheduleRepository.deleteById(id);
    }
}
