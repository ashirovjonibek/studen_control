package uz.controlstudentserver.servise;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.AddressDto;
import uz.controlstudentserver.entity.Address;
import uz.controlstudentserver.entity.District;
import uz.controlstudentserver.entity.enums.AddressType;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.AddressRepository;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DistrictService districtService;


    public ApiResponse saveOrEdit(AddressDto dto){
        try {
            var address=new Address();
            if (dto.getId()!=null) address=addressRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("address not found"));
            address.setAddress(dto.getAddress());
            address.setAddress_type(dto.getAddrType().equals("home")? AddressType.HOME_PLACE:AddressType.BIRTH_PLACE);
            address.setDistrict((District) districtService.getOne(dto.getDistrictId()).getObject());
            address=addressRepository.save(address);
            return new ApiResponse("Success",true,address);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse getOne(UUID uuid){
        try {
            Address address = addressRepository.findById(uuid).orElseThrow(() -> new IllegalStateException("Error find address"));
            return new ApiResponse("success",true,address);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
