package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.enums.EduType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduDto {
    private Integer id;

    private String name;

    private EduType eduType;

    private Long startDate;

    private Long endDate;

    private boolean complete;

    public EduDto(String name, EduType eduType, Long startDate, Long endDate, boolean complete) {
        this.name = name;
        this.eduType = eduType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
    }
}
