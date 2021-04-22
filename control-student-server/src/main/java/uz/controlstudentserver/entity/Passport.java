package uz.controlstudentserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passport {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String surname;

    private String firstName;

    private String fatherName;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Attachment attachment;

    private String birthDate;

    private String nationality;

    private String issuing_authority;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    private String sex;

    private String docType;

    private String passportNumber;

    private String dataOfIssue;

    private String dateOfExpire;

    private String authority;

}
