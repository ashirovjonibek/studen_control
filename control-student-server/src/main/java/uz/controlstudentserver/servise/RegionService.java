package uz.controlstudentserver.servise;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.RegionDto;
import uz.controlstudentserver.entity.Country;
import uz.controlstudentserver.entity.Region;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.CountryRepository;
import uz.controlstudentserver.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;


    public ApiResponse saveOrEdit(RegionDto dto){
       try {
           var region=new Region();
           if (dto.getId()!=null){
               region=regionRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("region not found"));
           }
           region.setName(dto.getName());
           region.setCountry(countryRepository.findById(dto.getCountryId()).orElseThrow(()->new IllegalStateException("country not found")));
           Region save = regionRepository.save(region);
           return new ApiResponse(dto.getId()==null?"region saved":"region edited",true,save);
       }catch (Exception e){
           e.printStackTrace();
           return new ApiResponse("Error save region",false);
       }
    }

    public ApiResponse getOne(Integer id){
        try {
            return new ApiResponse("success", true,regionRepository.findById(id).orElseThrow(()->new IllegalStateException("region not found")));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
    public ApiResponse findAll(){
        try {
            return new ApiResponse("Success",true,regionRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse findByCountry(Integer id){
        try {
            List<Region> regionList = regionRepository.findAllByCountry(countryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Error find country")));
            return new ApiResponse("success",true, regionList);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
