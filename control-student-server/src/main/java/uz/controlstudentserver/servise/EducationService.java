package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.EduDto;
import uz.controlstudentserver.entity.Address;
import uz.controlstudentserver.entity.Education;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.EducationRepository;

@Service
public class EducationService {
    @Autowired
    EducationRepository educationRepository;

    @Autowired
    AddressService addressService;

    public ApiResponse saveOrEdit(EduDto dto){
        try {
            Education education=new Education();
            if (dto.getId()!=null){
                education=educationRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("edu not found"));
            }
            if (dto.getAddresId()!=null){
                education.setAddress(((Address) addressService.getOne(dto.getAddresId()).getObject()));
            }
            education.setComplete(dto.isComplete());
            education.setEduType(dto.getEduType());
            education.setEndDate(dto.getEndDate());
            education.setName(dto.getName());
            education.setStartDate(dto.getStartDate());
            Education save = educationRepository.save(education);
            return new ApiResponse("Ok",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse getAll(){
        return new ApiResponse("ok", true,educationRepository.findAll());
    }

    public ApiResponse getOne(Integer id){
        try {
            Education education = educationRepository.findById(id).orElseThrow(() -> new IllegalStateException("edu not found"));
            return new ApiResponse("ok",true,education);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }


}
