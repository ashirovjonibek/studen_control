package uz.controlstudentserver.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.controlstudentserver.entity.Attachment;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.AttachmentRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse save(MultipartFile multipartFile) {
        Attachment attachment=new Attachment();
        attachment.setName(multipartFile.getOriginalFilename());
        attachment.setContentType(multipartFile.getContentType());
        attachment.setExt(fileExt(multipartFile.getOriginalFilename()));
        attachment.setSize(multipartFile.getSize());
        try {
            attachment.setBytes(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Attachment save = attachmentRepository.save(attachment);

        return new ApiResponse("success",true,save);
    }

    public String fileExt(String fileName){
        String ext=null;
        if (fileName!=null&&!fileName.isEmpty()){
            int dot=fileName.lastIndexOf(".");
            if (dot>0 && (dot<=fileName.length()-2)){
                ext=fileName.substring(dot+1);
            }
        }
        return ext;
    }

    public ApiResponse getById(UUID attachmentId){
        try {
            Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new IllegalStateException("file not found"));
        return new ApiResponse("success", true,attachment);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

}
