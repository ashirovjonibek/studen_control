package uz.controlstudentserver.servise;

import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.UserDto;
import uz.controlstudentserver.entity.Role;
import uz.controlstudentserver.entity.User;

import java.util.stream.Collectors;
@Service
public class DtoService {

    public UserDto userDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList())
        );
    }
}
