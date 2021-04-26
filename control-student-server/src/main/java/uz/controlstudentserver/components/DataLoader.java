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
        roles.add(roleRepository.save(new Role(RoleName.ROLE_ADMIN)));
        User admin=new User("admin", passwordEncoder.encode("admin"),new HashSet<>(roles));
        userRepository.save(admin);
    }
}
