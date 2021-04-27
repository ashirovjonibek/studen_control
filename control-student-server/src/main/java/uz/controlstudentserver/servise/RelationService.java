package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.RelationsDto;
import uz.controlstudentserver.entity.Address;
import uz.controlstudentserver.entity.Relations;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.RelationsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RelationService {
    @Autowired
    RelationsRepository relationsRepository;

    @Autowired
    AddressService addressService;

    public ApiResponse saveOrEdit(RelationsDto relationsDto){
        try {
            List<Address> addresses=addressService.addresses(relationsDto.getAddresses());
            Relations relations=new Relations();
            if (relationsDto.getId()!=null)
                relations = relationsRepository.findById(relationsDto.getId()).orElseThrow(() -> new IllegalStateException("Relation not Found"));
            relations.setAddresses(addresses);
            relations.setBirthDate(relationsDto.getBirthDate());
            relations.setEMail(relationsDto.getEMail());
            relations.setFullName(relationsDto.getFullName());
            relations.setJob(relationsDto.getJob());
            relations.setKinship(relationsDto.getKinship());
            Relations save = relationsRepository.save(relations);
            return new ApiResponse("success saved relation",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAllFromIds(List<UUID> uuids){
        try {
            return new ApiResponse("success",true,relationsRepository.findAllById(uuids));
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
