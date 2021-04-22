package uz.controlstudentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.controlstudentserver.entity.Role;
import uz.controlstudentserver.entity.enums.RoleName;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private RoleName roleName;
    private List<RoleName> roleNameList;

    public UserDto(UUID id, String firstName, String lastName, String username, List<RoleName> roleNameList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleNameList = roleNameList;
    }
}
