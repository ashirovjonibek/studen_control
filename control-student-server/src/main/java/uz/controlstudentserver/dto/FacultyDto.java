package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Education;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDto {
    private Integer id;

    private String name;

    private Integer educationId;

    public FacultyDto(String name, Integer educationId) {
        this.name = name;
        this.educationId = educationId;
    }
}
