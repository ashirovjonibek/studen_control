package uz.controlstudentserver.servise;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.DistrictDto;
import uz.controlstudentserver.entity.District;
import uz.controlstudentserver.entity.Region;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.DistrictRepository;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    RegionService regionService;

    public ApiResponse saveOrEdit(DistrictDto districtDto){
        try {
            var district=new District();
            if (districtDto.getId()!=null){
                district=districtRepository.findById(districtDto.getId()).orElseThrow(()->new IllegalStateException("district not found"));
            }
            district.setName(districtDto.getName());
            district.setRegion((Region) regionService.getOne(districtDto.getRegionId()).getObject());
            District save = districtRepository.save(district);
            return new ApiResponse("success",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse getOne(Integer id){
        try {
            return new ApiResponse("success",true,districtRepository.findById(id).orElseThrow(()->new IllegalStateException("district not found")));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        try {
            return new ApiResponse("Success",true,districtRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse findByRegion(Integer regionId){
        try {
            List<District> allByRegion = districtRepository.findAllByRegion((Region) regionService.getOne(regionId).getObject());
            return new ApiResponse("success",true,allByRegion);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
