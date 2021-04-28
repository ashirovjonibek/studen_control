package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Faculty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionDto {
    private Integer id;

    private String name;

    private Integer faculty;

    public DirectionDto(String name, Integer faculty) {
        this.name = name;
        this.faculty = faculty;
    }
}
