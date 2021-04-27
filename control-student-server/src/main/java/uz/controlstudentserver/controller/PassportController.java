package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.PassportDto;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.servise.PassportService;

import java.util.UUID;

@RestController
@RequestMapping("/api/passport")
public class PassportController {
    @Autowired
    PassportService passportService;

    @GetMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody PassportDto dto){
        ApiResponse response = passportService.saveOrEdit(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        ApiResponse byId = passportService.findById(id);
        return ResponseEntity.ok(byId);
    }
}
