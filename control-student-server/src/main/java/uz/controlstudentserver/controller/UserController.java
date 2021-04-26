package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.controlstudentserver.payload.ApiResponse;
import uz.controlstudentserver.servise.UserServise;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserServise userServise;

    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse response=userServise.getAll();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN',ROLE_TEACHER})")
    @GetMapping("/findUserByGroup")
    public HttpEntity<?> findAllUsrByGroup(@RequestParam Integer groupId){
        ApiResponse response = userServise.findByUserGroupId(groupId);
        return ResponseEntity.ok(response);
    }


}
