package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleOfVisitsDto {
    private UUID id;

    private List<UUID> studentIds;

    private UUID teacherId;

    private Long arrivalTime;

    private Long delay;

    private String course_duration;

    public ScheduleOfVisitsDto(List<UUID> studentIds, UUID teacherId, Long arrivalTime, Long delay, String course_duration) {
        this.studentIds = studentIds;
        this.teacherId = teacherId;
        this.arrivalTime = arrivalTime;
        this.delay = delay;
        this.course_duration = course_duration;
    }
}
