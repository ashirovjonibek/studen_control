package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.UserDto;
import uz.controlstudentserver.entity.*;
import uz.controlstudentserver.entity.enums.RoleName;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.*;
import uz.controlstudentserver.utils.CommonUtills;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServise {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PassportRepository passportRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    GroupsRepository groupsRepository;


    public ApiResponse saveOrEditUser(UserDto userDto){
        User user=new User();
        try {
            if (userDto.getId()!=null){
                user = userRepository.findById(userDto.getId()).orElseThrow(() -> new IllegalStateException("User topilmadi"));
            }
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUsername());
            Passport passport = passportRepository.findById(userDto.getId()).orElseThrow(() -> new IllegalStateException("Passport mavjud emas"));
            user.setPassport(passport);
            user.setAccountNonBlocked(userDto.isAccountNonBlocked());
            user.setAccountNonExpired(userDto.isAccountNonExpired());
            user.setCredentialNonExpired(userDto.isCredentialNonExpired());
            user.setEnabled(userDto.isEnabled());
            Reference reference = referenceRepository.findById(userDto.getReferenceId()).orElseThrow(() -> new IllegalStateException("Malumotnoma topilmadi"));
            user.setReference(reference);
            List<Role> roles=new ArrayList<>();
            for (int i = 0; i < userDto.getRoleNameList().size(); i++) {
                roles.add(roleRepository.findByRoleName(userDto.getRoleNameList().get(i)));
            }
            user.setRoles(new HashSet<>(roles));
            User user1 = userRepository.save(user);

            return new ApiResponse(userDto.getId()!=null?"Tahrirlandi":"Saqlandi",true, user1);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Userni saqlashda xatolik",false);
        }
    }

    public ApiResponse getAll() {
        return new ApiResponse("Success",true,userRepository.findAll());
    }

    public ApiResponse findByUserGroupId(Integer groupId) {
        try {
            Groups groups = groupsRepository.findById(groupId).orElseThrow(()->new IllegalStateException("group not found"));
            List<Reference> referenceList = referenceRepository.findAllByGroups(groups);
            List<User>users=new ArrayList<>();
            for (int i = 0; i < referenceList.size(); i++) {
                users.add(userRepository.findByReference(referenceList.get(i)));
            }
            return new ApiResponse("Success",true,users);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse findAllUsersByRole(RoleName roleName) {
        List<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName))) {
                userList.add(users.get(i));
            }
        }
        return new ApiResponse("Success",true,userList);
    }

    public ApiResponse allByPage(Integer page, Integer size) throws IllegalAccessException {
        Page<User> all = userRepository.findAll(CommonUtills.getPageableByCreatedAtDesc(page, size));
        return new ApiResponse("Ok",true,all.getContent().stream().map(item->User.class).collect(Collectors.toList()),all.getTotalElements(),all.getTotalPages());
    }
}
