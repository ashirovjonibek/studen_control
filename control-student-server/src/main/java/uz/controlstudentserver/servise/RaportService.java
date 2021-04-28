package uz.controlstudentserver.servise;

import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.controlstudentserver.dto.RaportDto;
import uz.controlstudentserver.entity.Education;
import uz.controlstudentserver.entity.Raport;
import uz.controlstudentserver.entity.User;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.RaportRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RaportService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    RaportRepository raportRepository;

    @Autowired
    UserServise userServise;


    public ApiResponse saveOrEdit(RaportDto dto){
        try {

            Raport raport=new Raport();
            User student= ((User) userServise.getOne(dto.getStudent()).getObject());
            User reporter= ((User) userServise.getOne(dto.getReporter()).getObject());
            raport.setDescription(dto.getDescription());
            raport.setResolverEmail(dto.getResolverEmail());
            raport.setReporter(reporter);
            raport.setStudent(student);
            Raport save = raportRepository.save(raport);
            sendMessageByEmail(raport);
            return new ApiResponse("Saved",true,save);
            }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse findByStudent(UUID uuid){
        try {
            List<Raport> raports=raportRepository.findAllByStudent(((User) userServise.getOne(uuid).getObject()));
            return new ApiResponse("ok",true,raports);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse findByReporter(UUID uuid){
        try {
            List<Raport> raports=raportRepository.findAllByReporter(((User) userServise.getOne(uuid).getObject()));
            return new ApiResponse("ok",true,raports);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }

    public ApiResponse findAll(){
        return new ApiResponse("ok",true,raportRepository.findAll());
    }

    public ApiResponse getOne(UUID id){
        try {
            Raport raport = raportRepository.findById(id).orElseThrow(() -> new IllegalStateException("raport Not Found"));
            return new ApiResponse("ok",true,raport);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    void sendMessageByEmail(Raport raport) {

        User user=raport.getStudent();
        Education education=user.getReference().getGroups().getDirection().getFaculty().getEducation();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(raport.getResolverEmail());
        msg.setSubject(education.getName());
        msg.setText(user.getFirstName()+
                " "+user.getLastName()+
                " "+user.getPassport().getFatherName()+
                " ga "+user.getReference().getGroups()
                .getDirection()
                .getFaculty()
                .getEducation().getName()+
                " dan bildirgi\n"+
                "Bildirgi chiqarilgan vaqt: "+new DataFormat(new Date(raport.getReportDate()).toString())+"\n"+
                raport.getDescription()+"\n\n\n"+
                "Bildirgi beruvchi shaxs: "+raport.getReporter().getFirstName()+" "+raport.getReporter().getLastName()

        );

        javaMailSender.send(msg);

    }
}
