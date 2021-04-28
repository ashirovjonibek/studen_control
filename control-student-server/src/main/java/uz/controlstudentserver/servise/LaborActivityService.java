package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.entity.LaborActivity;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.LaborActivityRepository;

@Service
public class LaborActivityService {
    @Autowired
    LaborActivityRepository laborActivityRepository;

    public ApiResponse save(LaborActivity laborActivity){
        try {
            return new ApiResponse("saved",true,laborActivityRepository.save(laborActivity));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findById(Integer id){
        try {
            LaborActivity laborActivity = laborActivityRepository.findById(id).orElseThrow(() -> new IllegalStateException("labo activity not found"));
            return new ApiResponse("ok",true,laborActivity);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,laborActivityRepository.findAll());
    }
}
