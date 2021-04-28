package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.controlstudentserver.payload.ResToken;
import uz.controlstudentserver.payload.SignIn;
import uz.controlstudentserver.servise.AuthService;

@RestController
@CrossOrigin
@RequestMapping("auth/api")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping("/login")
    public HttpEntity<?> login(@RequestParam String un,@RequestParam String pr){

        ResToken resToken=authService.signIn(new SignIn(un,pr));
        return ResponseEntity.status(resToken!=null?200:401).body(resToken);
    }
    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
    @GetMapping("/searchUser/{search}")
    public HttpEntity<?> searchUser(@PathVariable String search){
        return ResponseEntity.ok(authService.searchUser(search));
    }

//    @PreAuthorize("hasAnyRole({'ROLE_DEAN','ROLE_DEPUTY_DEAN'})")
//    @GetMapping("/all")
//    public HttpEntity<?> searchUser(){
//        return ResponseEntity.ok(authService.all());
//    }


}
