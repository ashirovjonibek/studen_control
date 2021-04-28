package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.ReferenceDto;
import uz.controlstudentserver.entity.Reference;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.repository.ReferenceRepository;
import uz.controlstudentserver.servise.ReferenceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/reference")
public class ReferenceController {

    @Autowired
    ReferenceService referenceService;
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN','ROLE_STUDENT','ROLE_TEACHER'})")
    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody ReferenceDto referenceDto){
        ApiResponse response = referenceService.saveOrEdit(referenceDto);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/findById/{id}")
    public HttpEntity<?> findById(@PathVariable UUID id){
        ApiResponse byId = referenceService.findById(id);
        return ResponseEntity.ok(byId);
    }
}
