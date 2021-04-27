package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.PassportDto;
import uz.controlstudentserver.entity.Attachment;
import uz.controlstudentserver.entity.Passport;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.PassportRepository;

import java.util.UUID;

@Service
public class PassportService {

    @Autowired
    PassportRepository passportRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    AttachmentService attachmentService;

    public boolean exitsByNum(String passNum){
        return passportRepository.existsByPassportNumber(passNum);
    }

    public ApiResponse saveOrEdit(PassportDto dto){
        try {
            Passport passport=new Passport();
            if (dto.getId()!=null){
                passport = passportRepository.findById(dto.getId()).orElseThrow(() -> new IllegalStateException("Passport not found"));
            }
            passport.setAddress(addressService.addresses(dto.getAddresses()));
            passport.setAttachment(((Attachment) attachmentService.getById(dto.getAttachmentId()).getObject()));
            passport.setAuthority(dto.getAuthority());
            passport.setBirthDate(dto.getBirthDate());
            passport.setDataOfIssue(dto.getDataOfIssue());
            passport.setDateOfExpire(dto.getDateOfExpire());
            passport.setDocType(dto.getDocType());
            passport.setFatherName(dto.getFatherName());
            passport.setFirstName(dto.getFirstName());
            passport.setIssuing_authority(dto.getIssuing_authority());
            passport.setNationality(dto.getNationality());
            passport.setPassportNumber(dto.getPassportNumber());
            passport.setSex(dto.getSex());
            passport.setSurname(dto.getSurname());
            Passport save = passportRepository.save(passport);
            return new ApiResponse(dto.getId()!=null?"edited":"saved",true,save);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse findById(UUID uuid){
        try {
            Passport passport = passportRepository.findById(uuid).orElseThrow(() -> new IllegalStateException("passport not found"));
            return new ApiResponse("ok",true,passport);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }
}
