package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.ReferenceDto;
import uz.controlstudentserver.entity.*;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.ReferenceRepository;
import uz.controlstudentserver.repository.RelationsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReferenceService {
    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    RelationsRepository relationsRepository;

    @Autowired
    LaborActivityService laborActivityService;

    @Autowired
    PartyService partyService;

    @Autowired
    GroupService groupService;



    public ApiResponse saveOrEdit(ReferenceDto dto){
        try {
            Reference reference=new Reference();
            List<Relations> relations=new ArrayList<>();
            List<LaborActivity> laborActivities=new ArrayList<>();
            if (dto.getId()!=null){
                reference=referenceRepository.findById(dto.getId()).orElseThrow(()->new IllegalStateException("reference not found"));
            }
            reference.setAcademicDegree(dto.getAcademicDegree());
            reference.setAwards(dto.getAwards());
            reference.setForeignLang(dto.getForeignLang());
            reference.setPolitical_party(dto.getPolitical_party());
            for (int i = 0; i < dto.getRelations().size(); i++) {
                relations.add(relationsRepository.findById(dto.getRelations().get(i)).orElseThrow(()->new IllegalStateException("not found")));
            }
            reference.setRelations(relations);
            for (int i = 0; i < dto.getLaborActivities().size(); i++) {
                laborActivities.add(((LaborActivity) laborActivityService.findById(dto.getLaborActivities().get(i)).getObject()));
            }
            reference.setLaborActivities(laborActivities);
            reference.setParty(((Party) partyService.findById(dto.getParty()).getObject()));
            reference.setGroups((Groups) groupService.getOne(dto.getGroupId()).getObject());
            Reference reference1=referenceRepository.save(reference);
            return new ApiResponse("ok",true,reference1);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findById(UUID uuid){
        try {
            Reference reference = referenceRepository.findById(uuid).orElseThrow(() -> new IllegalStateException("reference not found"));
            return new ApiResponse("ok",true,reference);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

}
