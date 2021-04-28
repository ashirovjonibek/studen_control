package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.entity.Party;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.PartyRepository;

@Service
public class PartyService {

    @Autowired
    PartyRepository partyRepository;

    public ApiResponse save(Party party){
        try {
            Party save = partyRepository.save(party);
            return new ApiResponse("ok",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,partyRepository.findAll());
    }

    public ApiResponse findById(Integer id){
        try {
            Party party = partyRepository.findById(id).orElseThrow(() -> new IllegalStateException("party not found"));
            return new ApiResponse("ok",true,party);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
