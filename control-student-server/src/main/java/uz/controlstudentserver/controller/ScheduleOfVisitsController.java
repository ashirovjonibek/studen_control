package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.ScheduleOfVisitsDto;
import uz.controlstudentserver.servise.ScheduleOfVisitsService;

import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("api/sov")
public class ScheduleOfVisitsController {
    @Autowired
    ScheduleOfVisitsService scheduleOfVisitsService;
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ScheduleOfVisitsDto dto){
        return ResponseEntity.ok(scheduleOfVisitsService.saveOrEdit(dto));
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN','ROLE_TEACHER','ROLE_STUDENT'})")
    @GetMapping("/getAllStudentId/{id}")
    public HttpEntity<?> getAllStudentId(@PathVariable UUID id){
        return ResponseEntity.ok(scheduleOfVisitsService.getByStudentId(id));
    }
}
