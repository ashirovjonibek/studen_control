package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.FacultyDto;
import uz.controlstudentserver.entity.Education;
import uz.controlstudentserver.entity.Faculty;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    EducationService educationService;

    public ApiResponse saveOrEdit(FacultyDto dto){
        try {
            Faculty faculty=new Faculty();
            if (dto.getId()!=null){
                faculty=facultyRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("faculty not found"));
            }
            faculty.setEducation(((Education) educationService.getOne(dto.getEducationId()).getObject()));
            faculty.setName(dto.getName());
            Faculty save = facultyRepository.save(faculty);
            return new ApiResponse("ok", true, save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,facultyRepository.findAll());
    }

    public ApiResponse getOne(Integer id){
        try {
            Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new IllegalStateException("fnf"));
            return new ApiResponse("success",true,faculty);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getByEdu(Education education){
        try {
            List<Faculty> allByEducation = facultyRepository.findAllByEducation(education);
            return new ApiResponse("ok",true,allByEducation);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
