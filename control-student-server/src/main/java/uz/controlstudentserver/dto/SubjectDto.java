package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Integer id;

    private String name;

    private List<Integer> groupIds;

    private List<UUID> teacherIds;

    public SubjectDto(String name, List<Integer> groupIds, List<UUID> teacherIds) {
        this.name = name;
        this.groupIds = groupIds;
        this.teacherIds = teacherIds;
    }
}
