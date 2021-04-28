package uz.controlstudentserver.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.tools.jar.CommandLine;
import uz.controlstudentserver.entity.Role;
import uz.controlstudentserver.entity.User;
import uz.controlstudentserver.entity.enums.RoleName;
import uz.controlstudentserver.repository.RoleRepository;
import uz.controlstudentserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles=new ArrayList<>();
        Role role_admin = roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        roleRepository.save(new Role(RoleName.ROLE_TEACHER));
        roleRepository.save(new Role(RoleName.ROLE_DEPUTY_DEAN));
        roleRepository.save(new Role(RoleName.ROLE_DEAN));
        roleRepository.save(new Role(RoleName.ROLE_STUDENT));
        roles.add(roleRepository.save(role_admin));
        User admin=new User("admin", passwordEncoder.encode("admin"),new HashSet<>(roles));
        userRepository.save(admin);
    }
}
