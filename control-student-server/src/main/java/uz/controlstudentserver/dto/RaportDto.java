package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaportDto {
    private UUID id;

    private UUID student;

    private UUID reporter;

    private String description;

    private String resolverEmail;

    public RaportDto(UUID student, UUID reporter, String description, String resolverEmail) {
        this.student = student;
        this.reporter = reporter;
        this.description = description;
        this.resolverEmail = resolverEmail;
    }
}
