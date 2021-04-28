package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.GroupDto;
import uz.controlstudentserver.entity.Direction;
import uz.controlstudentserver.entity.Groups;
import uz.controlstudentserver.entity.User;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.GroupsRepository;
import uz.controlstudentserver.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupsRepository groupsRepository;

    @Autowired
    DirectionService directionService;

    @Autowired
    UserRepository userRepository;

    public ApiResponse saveOrEdit(GroupDto dto){
        try {
            Groups groups =new Groups();
            if (dto.getId()!=null){
               groups= groupsRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("group not found"));
            }
            groups.setCourse_number(dto.getCourse_number());
            groups.setDirection(((Direction) directionService.getOne(dto.getDirectionId()).getObject()));
            groups.setNumber(dto.getNumber());
            List<User> users=new ArrayList<>();
            for (int i = 0; i < dto.getTeachersIds().size(); i++) {
                users.add(userRepository.findById(dto.getTeachersIds().get(i)).orElseThrow(()->new IllegalStateException("User Not found")));
            }
            groups.setTeachers(users);
            Groups save = groupsRepository.save(groups);
            return new ApiResponse("ok",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getOne(Integer id){
        try {
            Groups group_not_found = groupsRepository.findById(id).orElseThrow(() -> new IllegalStateException("group not found"));
            return new ApiResponse("ok",true,group_not_found);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,groupsRepository.findAll());
    }

    public ApiResponse findByDirection(Direction direction){
        return new ApiResponse("ok",true,groupsRepository.findAllByDirection(direction));
    }

    public ApiResponse findByTeacher(User user){
        return new ApiResponse("ok",true,groupsRepository.findAllByTeachers(user));
    }
}
