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

    public PassportDto(String surname, String firstName, String fatherName, UUID attachmentId, String birthDate, String nationality, String issuing_authority, List<UUID> addresses, String sex, String docType, String passportNumber, String dataOfIssue, String dateOfExpire, String authority) {
        this.surname = surname;
        this.firstName = firstName;
        this.fatherName = fatherName;
        this.attachmentId = attachmentId;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.issuing_authority = issuing_authority;
        this.addresses = addresses;
        this.sex = sex;
        this.docType = docType;
        this.passportNumber = passportNumber;
        this.dataOfIssue = dataOfIssue;
        this.dateOfExpire = dateOfExpire;
        this.authority = authority;
    }
}
