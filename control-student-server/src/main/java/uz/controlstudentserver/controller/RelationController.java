package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.dto.RelationsDto;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.servise.RelationService;

@RestController
@RequestMapping("api/relations")
public class RelationController {
    @Autowired
    RelationService relationService;
    @PreAuthorize("hasRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN','ROLE-TEACHER','ROLE_STUDENT'})")
    @PostMapping("/saveOrEditRelations")
    public HttpEntity<?> saveOrEditRelations(@RequestBody RelationsDto dto){
        ApiResponse response = relationService.saveOrEdit(dto);
        return ResponseEntity.ok(response);
    }
}
