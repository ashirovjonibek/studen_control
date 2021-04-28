package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.controlstudentserver.entity.Attachment;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.servise.AttachmentService;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("api/image")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/save")
    public HttpEntity<?>save(@RequestParam MultipartFile multipartFile){
        ApiResponse save = attachmentService.save(multipartFile);
        return ResponseEntity.status(save.isSuccess()?200:409).body(save);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("get/{id}")
    public HttpEntity<?> getById(@PathVariable UUID id) {
        ApiResponse byId = attachmentService.getById(id);
        return ResponseEntity.status(byId.isSuccess()?200:409).body(byId);
    }


}
