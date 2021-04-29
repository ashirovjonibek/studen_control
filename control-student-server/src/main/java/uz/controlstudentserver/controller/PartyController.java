package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.entity.Party;
import uz.controlstudentserver.servise.PartyService;
@CrossOrigin
@RestController
@RequestMapping("api/party")
public class PartyController {
    @Autowired
    PartyService partyService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody Party party){
        return ResponseEntity.ok(partyService.save(party));
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/findAll")
    public HttpEntity<?> findAll(){
        return ResponseEntity.ok(partyService.findAll());
    }
}