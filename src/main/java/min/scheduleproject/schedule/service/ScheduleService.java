package min.scheduleproject.schedule.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import min.scheduleproject.comment.dto.response.CommentResponseDto;
import min.scheduleproject.comment.entity.Comment;
import min.scheduleproject.comment.repository.CommentRepository;
import min.scheduleproject.common.exception.CustomException;
import min.scheduleproject.common.exception.ErrorCode;
import min.scheduleproject.schedule.dto.request.ScheduleCreateRequestDto;
import min.scheduleproject.schedule.dto.request.ScheduleModifyRequestDto;
import min.scheduleproject.schedule.dto.response.ScheduleGetResponseDto;
import min.scheduleproject.schedule.dto.response.ScheduleResponseDto;
import min.scheduleproject.schedule.entity.Schedule;
import min.scheduleproject.schedule.repository.ScheduleRepository;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(HttpSession session,
                                              ScheduleCreateRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        User user = userRepository.findById(uid).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule schedule = Schedule.from(user, dto);
        Schedule created = scheduleRepository.save(schedule);

        return ScheduleResponseDto.from(created);
    }

    public ScheduleGetResponseDto findScheduleByScheduleId(long scheduleId) {
        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        List<Comment> comments = commentRepository.findAllBySchedule_ScheduleIdOrderByCreatedAtAsc(found.getScheduleId());
        List<CommentResponseDto> commentResponseDtos = comments.stream().map(CommentResponseDto::of).toList();

        return ScheduleGetResponseDto.from(found, commentResponseDtos);
    }

    public Page<ScheduleGetResponseDto> findAllSchedulesByUid(Long uid, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        Page<Schedule> foundSchedules = scheduleRepository.findAllByUserUidOrderByModifiedAtDesc(pageable, uid);

        List<ScheduleGetResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : foundSchedules) {
            List<Comment> comments = commentRepository.findAllBySchedule_ScheduleIdOrderByCreatedAtAsc(schedule.getScheduleId());

            List<CommentResponseDto> commentResponseDtos = comments.stream().map(CommentResponseDto::of).toList();

            dtos.add(ScheduleGetResponseDto.from(schedule, commentResponseDtos));
        }

        return new PageImpl<>(dtos, pageable, foundSchedules.getTotalElements());
    }

    public Page<ScheduleGetResponseDto> findAllScheduleByTitle(String title, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        Page<Schedule> foundSchedules = scheduleRepository.findAllByTitleOrderByModifiedAtDesc(pageable, title);

        List<ScheduleGetResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : foundSchedules) {
            List<Comment> comments = commentRepository.findAllBySchedule_ScheduleIdOrderByCreatedAtAsc(schedule.getScheduleId());

            List<CommentResponseDto> commentResponseDtos = comments.stream().map(CommentResponseDto::of).toList();

            dtos.add(ScheduleGetResponseDto.from(schedule, commentResponseDtos));
        }

        return new PageImpl<>(dtos, pageable, foundSchedules.getTotalElements());
    }

    @Transactional
    public ScheduleResponseDto modifySchedule(HttpSession session, Long scheduleId, ScheduleModifyRequestDto dto) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!ObjectUtils.nullSafeEquals(uid, found.getUser().getUid())) throw new CustomException(ErrorCode.USER_ID_MISMATCH);

        found.modifySchedule(dto.title(), dto.contents());

        return ScheduleResponseDto.from(found);
    }

    @Transactional
    public void deleteSchedule(HttpSession session, Long scheduleId) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        Schedule found = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if(commentRepository.existsBySchedule_ScheduleId(found.getScheduleId())) throw new CustomException(ErrorCode.SCHEDULE_HAS_COMMENTS);
        if(!ObjectUtils.nullSafeEquals(uid, found.getUser().getUid())) throw new CustomException(ErrorCode.USER_ID_MISMATCH);

        scheduleRepository.deleteById(scheduleId);
    }


}
