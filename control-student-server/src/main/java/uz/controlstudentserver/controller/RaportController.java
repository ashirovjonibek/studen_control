package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.RaportDto;
import uz.controlstudentserver.repository.UserRepository;
import uz.controlstudentserver.servise.RaportService;

import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN','ROLE_TEACHER'})")
@RequestMapping("api/raport")
public class RaportController {
    @Autowired
    RaportService raportService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody RaportDto dto){
        return ResponseEntity.ok(raportService.saveOrEdit(dto));
    }

    @GetMapping("/getStudentRaports/{id}")
    public HttpEntity<?> getStudentRaports(@PathVariable UUID id){
        return ResponseEntity.ok(raportService.findByStudent(id));
    }

    @GetMapping("/getReporterRaports/{id}")
    public HttpEntity<?> getReporterRaports(@PathVariable UUID id){
        return ResponseEntity.ok(raportService.findByReporter(id));
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(raportService.findAll());
    }

    @GetMapping("/findById/{id}")
    public HttpEntity<?> findById(@PathVariable UUID id){
        return ResponseEntity.ok(raportService.getOne(id));
    }
}
