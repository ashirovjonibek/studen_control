package uz.controlstudentserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn){
        ResToken resToken=authService.signIn(signIn);
        return ResponseEntity.status(resToken!=null?200:401).body(resToken);
    }

    @GetMapping("/searchUser/{search}")
    public HttpEntity<?> searchUser(@PathVariable String search){
        return ResponseEntity.ok(authService.searchUser(search));
    }
    @GetMapping("/all")
    public HttpEntity<?> searchUser(){
        return ResponseEntity.ok(authService.all());
    }


}
