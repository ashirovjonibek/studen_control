package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Direction;
import uz.controlstudentserver.entity.User;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Integer id;

    private String number;

    private int course_number;

    private Integer directionId;

    private List<UUID> teachersIds;
}
