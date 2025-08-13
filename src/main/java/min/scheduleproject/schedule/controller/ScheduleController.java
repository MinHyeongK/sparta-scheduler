package min.scheduleproject.schedule.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.schedule.dto.request.ScheduleCreateRequestDto;
import min.scheduleproject.schedule.dto.request.ScheduleModifyRequestDto;
import min.scheduleproject.schedule.dto.response.ScheduleGetResponseDto;
import min.scheduleproject.schedule.dto.response.ScheduleResponseDto;
import min.scheduleproject.schedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(HttpSession session,
                                                              @Validated @RequestBody ScheduleCreateRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(session, dto));
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetResponseDto> findScheduleByScheduleId(@PathVariable long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findScheduleByScheduleId(scheduleId));
    }

    @GetMapping(params = "uid")
    public ResponseEntity<Page<ScheduleGetResponseDto>> findAllSchedulesByUid(@RequestParam(required = false) long uid,
                                                                              @RequestParam(defaultValue = "0") int pageNum,
                                                                              @RequestParam(defaultValue = "10") int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllSchedulesByUid(uid, pageNum, pageSize));
    }

    @GetMapping(params = "title")
    public ResponseEntity<Page<ScheduleGetResponseDto>> findAllScheduleByTitle(@RequestParam String title,
                                                                               @RequestParam(defaultValue = "0") int pageNum,
                                                                               @RequestParam(defaultValue = "10") int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllScheduleByTitle(title, pageNum, pageSize));
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(HttpSession session,
                                                              @PathVariable long scheduleId,
                                                              @Validated @RequestBody ScheduleModifyRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.modifySchedule(session, scheduleId, dto));
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(HttpSession session,
                               @PathVariable long scheduleId){
        scheduleService.deleteSchedule(session, scheduleId);
    }
}
