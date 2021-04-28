package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.DirectionDto;
import uz.controlstudentserver.entity.Direction;
import uz.controlstudentserver.entity.Faculty;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.DirectionRepository;

import java.util.List;

@Service
public class DirectionService {
    @Autowired
    DirectionRepository directionRepository;
    
    @Autowired
    FacultyService facultyService;
    
    public ApiResponse saveOrEdit(DirectionDto directionDto){
        try {
            Direction direction=new Direction();
            if (directionDto.getId()!=null){
                direction=directionRepository.findById(directionDto.getId()).orElseThrow(()->new IllegalStateException("direction not found"));
            }
            direction.setName(directionDto.getName());
            direction.setFaculty(((Faculty) facultyService.getOne(directionDto.getId()).getObject()));
            Direction save = directionRepository.save(direction);
            return new ApiResponse("ok",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,directionRepository.findAll());
    }

    public ApiResponse getOne(Integer id){
        try {
            Direction direction = directionRepository.findById(id).orElseThrow(() -> new IllegalStateException("direction not found"));
            return new ApiResponse("ok",true,direction);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getByFaculty(Faculty faculty){
        try {
            List<Direction> allByFaculty = directionRepository.findAllByFaculty(faculty);
            return new ApiResponse("ok",true,allByFaculty);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }


}
