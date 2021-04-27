package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Address;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationsDto {
    private UUID id;

    private String fullName;

    private String kinship;

    private List<UUID> addresses;

    private String job;

    private String eMail;

    private String birthDate;

    public RelationsDto(String fullName, String kinship, List<UUID> addresses, String job, String eMail, String birthDate) {
        this.fullName = fullName;
        this.kinship = kinship;
        this.addresses = addresses;
        this.job = job;
        this.eMail = eMail;
        this.birthDate = birthDate;
    }
}
