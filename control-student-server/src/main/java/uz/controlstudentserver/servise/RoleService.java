package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.controlstudentserver.entity.Role;
import uz.controlstudentserver.entity.enums.RoleName;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse getAll(){
        return new ApiResponse("ok",true,roleRepository.findAll());
    }

    public ApiResponse findById(Integer id){
        try {
            Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalStateException("role not found"));
            return new ApiResponse("ok",true,role);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getByRoleName(RoleName roleName){
        return new ApiResponse("ok",true,roleRepository.findByRoleName(roleName));
    }
}
