package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.ScheduleOfVisitsDto;
import uz.controlstudentserver.entity.ScheduleOfVisits;
import uz.controlstudentserver.entity.User;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.ScheduleOfVisitsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleOfVisitsService {
    @Autowired
    ScheduleOfVisitsRepository scheduleOfVisitsRepository;

    @Autowired
    UserServise userServise;

    public ApiResponse saveOrEdit(ScheduleOfVisitsDto dto){
        try {
            ScheduleOfVisits scheduleOfVisits=new ScheduleOfVisits();
            if (dto.getId()!=null){
                scheduleOfVisits=scheduleOfVisitsRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("not found"));
            }
            scheduleOfVisits.setArrivalTime(dto.getArrivalTime());
            scheduleOfVisits.setCourse_duration(dto.getCourse_duration());
            scheduleOfVisits.setDelay(dto.getDelay());
            List<User> users=new ArrayList<>();
            for (int i = 0; i < dto.getStudentIds().size(); i++) {
                users.add(((User) userServise.getOne(dto.getId()).getObject()));
            }
            scheduleOfVisits.setStudent(users);
            scheduleOfVisits.setTeacher(((User) userServise.getOne(dto.getId()).getObject()));
            ScheduleOfVisits save = scheduleOfVisitsRepository.save(scheduleOfVisits);
            return new ApiResponse("ok",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getOne(UUID uuid){
        try {
            ScheduleOfVisits scheduleOfVisits = scheduleOfVisitsRepository.findById(uuid).orElseThrow(() -> new IllegalStateException("not Fount"));
            return new ApiResponse("ok",true,scheduleOfVisits);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getByStudentId(UUID uuid){
        try {
            List<ScheduleOfVisits> allByStudent = scheduleOfVisitsRepository.findAllByStudent(((User) userServise.getOne(uuid).getObject()));
            return new ApiResponse("ok",true,allByStudent);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }
}
