package min.scheduleproject.comment.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.comment.dto.CommentRequestDto;
import min.scheduleproject.comment.dto.CommentResponseDto;
import min.scheduleproject.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}")
    public ResponseEntity<CommentResponseDto> createComment(HttpSession session,
                                                            @PathVariable long scheduleId,
                                                            @Validated @RequestBody CommentRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(session, scheduleId, dto));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentByScheduleId(@PathVariable long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentByScheduleId(scheduleId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentByCommentId(@PathVariable long commentId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByCommentId(commentId));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyComment(HttpSession session,
                                                            @PathVariable long commentId,
                                                            @Validated @RequestBody CommentRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.modifyComment(session, commentId, dto));
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(HttpSession session,
                              @PathVariable long commentId){
        commentService.deleteComment(session, commentId);
    }
}
