package min.scheduleproject.comment.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import min.scheduleproject.comment.dto.CommentRequestDto;
import min.scheduleproject.comment.dto.CommentResponseDto;
import min.scheduleproject.comment.entity.Comment;
import min.scheduleproject.comment.repository.CommentRepository;
import min.scheduleproject.common.exception.CustomException;
import min.scheduleproject.common.exception.ErrorCode;
import min.scheduleproject.schedule.entity.Schedule;
import min.scheduleproject.schedule.repository.ScheduleRepository;
import min.scheduleproject.user.entity.User;
import min.scheduleproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(HttpSession session, Long scheduleId, CommentRequestDto dto) {
        Long uid = (Long)session.getAttribute("LOGIN_USER");

        User user = userRepository.findById(uid).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        Comment comment = Comment.from(user, schedule, dto);

        return CommentResponseDto.of(commentRepository.save(comment));
    }

    public List<CommentResponseDto> getAllCommentByScheduleId(Long scheduleId) {
        List<Comment> comments = commentRepository.findBySchedule_ScheduleIdOrderByCreatedAtDesc(scheduleId);

        return comments.stream().map(CommentResponseDto::of).collect(Collectors.toList());
    }

    public CommentResponseDto getCommentByCommentId(Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId);

        return CommentResponseDto.of(comment);
    }

    @Transactional
    public CommentResponseDto modifyComment(HttpSession session, Long commentId, CommentRequestDto dto) {
    Long uid = (Long) session.getAttribute("LOGIN_USER");

    Comment comment = commentRepository.findByCommentId(commentId);

    if(!ObjectUtils.nullSafeEquals(uid, comment.getUser().getUid())) throw new CustomException(ErrorCode.USER_ID_MISMATCH);

    comment.modifyComment(dto.contents());

    return CommentResponseDto.of(comment);
    }

    @Transactional
    public void deleteComment(HttpSession session, long commentId) {
        Long uid = (Long) session.getAttribute("LOGIN_USER");

        Comment comment = commentRepository.findByCommentId(commentId);

        if(!ObjectUtils.nullSafeEquals(uid, comment.getUser().getUid())) throw new CustomException(ErrorCode.USER_ID_MISMATCH);

        commentRepository.deleteById(commentId);
    }
}
