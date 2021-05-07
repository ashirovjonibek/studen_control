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
    private String password;
    private UUID passportId;
    private UUID referenceId;
    private Integer eduId;
    private List<RoleName> roleNameList;
    private boolean accountNonBlocked;
    private boolean accountNonExpired;
    private boolean credentialNonExpired;
    private boolean enabled;


    public UserDto(String firstName, String lastName, String username, RoleName roleName, String password, UUID passportId, UUID referenceId, Integer eduId, List<RoleName> roleNameList, boolean accountNonBlocked, boolean accountNonExpired, boolean credentialNonExpired, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleName = roleName;
        this.password = password;
        this.passportId = passportId;
        this.referenceId = referenceId;
        this.eduId = eduId;
        this.roleNameList = roleNameList;
        this.accountNonBlocked = accountNonBlocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialNonExpired = credentialNonExpired;
        this.enabled = enabled;
    }

    public UserDto(UUID id, String firstName, String lastName, String username, List<RoleName> roleNameList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleNameList = roleNameList;
    }

    public UserDto(String firstName, String lastName, String username, RoleName roleName, String password, UUID passportId, UUID referenceId, List<RoleName> roleNameList, boolean accountNonBlocked, boolean accountNonExpired, boolean credentialNonExpired, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.roleName = roleName;
        this.password = password;
        this.passportId = passportId;
        this.referenceId = referenceId;
        this.roleNameList = roleNameList;
        this.accountNonBlocked = accountNonBlocked;
        this.accountNonExpired = accountNonExpired;
        this.credentialNonExpired = credentialNonExpired;
        this.enabled = enabled;
    }
}
