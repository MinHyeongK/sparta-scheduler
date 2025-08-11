package min.scheduleproject.schedule.controller;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.schedule.dto.*;
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
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Validated @RequestBody ScheduleCreateRequestDto dto){
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetResponseDto> findSchedule(@PathVariable long id){
        return new ResponseEntity<>(scheduleService.findSchedule(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleGetResponseDto>> findAllSchedules(@RequestParam(required = false) long uid){
        return new ResponseEntity<>(scheduleService.findAllSchedules(uid), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(@PathVariable long id,
                                                                   @RequestBody ScheduleModifyRequestDto dto){
        return new ResponseEntity<>(scheduleService.modifySchedule(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable long id,
                               @RequestBody ScheduleDeleteRequestDto dto){
        scheduleService.deleteSchedule(id, dto);
    }
}
