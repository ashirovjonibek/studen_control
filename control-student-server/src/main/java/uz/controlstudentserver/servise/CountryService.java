package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.entity.Country;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.CountryRepository;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public ApiResponse saveOrEdit(Country country){
        try {
            return new ApiResponse("success save country",true,countryRepository.save(country));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error save country",false);
        }
    }

    public ApiResponse getOne(Integer id){
        try {
            return new ApiResponse("ok",true,countryRepository.findById(id).orElseThrow(()->new IllegalStateException("country not found")));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        try {
            return new ApiResponse("ok",true,countryRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
