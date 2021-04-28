package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Groups;
import uz.controlstudentserver.entity.LaborActivity;
import uz.controlstudentserver.entity.Party;
import uz.controlstudentserver.entity.Relations;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceDto {

    private UUID id;

    private List<UUID> relations;

    private String academicDegree;

    private String awards;

    private Integer party;

    private String foreignLang;

    private String political_party;

    private List<Integer> laborActivities;

    private Integer groupId;

    public ReferenceDto(List<UUID> relations, String foreignLang, String academicDegree, String awards, Integer party, String political_party, List<Integer> laborActivities, Integer groupId) {
        this.relations = relations;
        this.academicDegree = academicDegree;
        this.awards = awards;
        this.foreignLang=foreignLang;
        this.party = party;
        this.political_party = political_party;
        this.laborActivities = laborActivities;
        this.groupId = groupId;
    }
}
