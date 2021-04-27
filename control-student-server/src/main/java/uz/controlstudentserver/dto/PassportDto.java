package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Address;
import uz.controlstudentserver.entity.Attachment;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {
    private UUID id;

    private String surname;

    private String firstName;

    private String fatherName;

    private UUID attachmentId;

    private String birthDate;

    private String nationality;

    private String issuing_authority;

    private List<UUID> addresses;

    private String sex;

    private String docType;
    
    private String passportNumber;

    private String dataOfIssue;

    private String dateOfExpire;

    private String authority;
}
