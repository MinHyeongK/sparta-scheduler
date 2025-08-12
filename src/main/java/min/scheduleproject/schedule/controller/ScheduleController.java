package min.scheduleproject.schedule.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.schedule.dto.request.ScheduleCreateRequestDto;
import min.scheduleproject.schedule.dto.request.ScheduleModifyRequestDto;
import min.scheduleproject.schedule.dto.response.ScheduleGetResponseDto;
import min.scheduleproject.schedule.dto.response.ScheduleResponseDto;
import min.scheduleproject.schedule.service.ScheduleService;
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
        return new ResponseEntity<>(scheduleService.createSchedule(session, dto), HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetResponseDto> findScheduleByScheduleId(@PathVariable long scheduleId){
        return new ResponseEntity<>(scheduleService.findScheduleByScheduleId(scheduleId), HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<ScheduleGetResponseDto>> findScheduleByTitle(@RequestParam String title){
        return new ResponseEntity<>(scheduleService.findScheduleByTitle(title), HttpStatus.OK);
    }

    @GetMapping(params = "uid")
    public ResponseEntity<List<ScheduleGetResponseDto>> findAllSchedulesByUid(@RequestParam(required = false) long uid){
        return new ResponseEntity<>(scheduleService.findAllSchedulesByUid(uid), HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(HttpSession session,
                                                              @PathVariable long scheduleId,
                                                              @Validated @RequestBody ScheduleModifyRequestDto dto){
        return new ResponseEntity<>(scheduleService.modifySchedule(session, scheduleId, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(HttpSession session,
                               @PathVariable long scheduleId){
        scheduleService.deleteSchedule(session, scheduleId);
    }
}
