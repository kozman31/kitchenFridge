package com.thekitchenfridge.users.controller;

import com.thekitchenfridge.security.entities.Authority;
import com.thekitchenfridge.users.service.AuthorityService;
import com.thekitchenfridge.users.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class Test {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public ResponseEntity<String> adminReply(){
        return new ResponseEntity<>("Hello Admin", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> userReply(){
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> standardReply(){
        return new ResponseEntity<>("Hello Everyone", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> saveAuth(Set<Authority> auth){
        authorityService.saveAuthorities(auth);
        return new ResponseEntity<>("auth saved", HttpStatus.OK);
    }
}
