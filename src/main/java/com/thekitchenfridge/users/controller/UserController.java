package com.thekitchenfridge.users.controller;

import com.thekitchenfridge.email.Email;
import com.thekitchenfridge.email.EmailService;
import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping(value="/register")
    public ResponseEntity<String> signup(@RequestBody UserProfileImpl userProfile){
        if(userDetailsService.registerNewUser(userProfile)){
            return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Username Already Exists", HttpStatus.OK);
    }

    @PostMapping(value="/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }

    @PostMapping(value="/user/update")
    public ResponseEntity<HttpStatus> updateUserRoles(@RequestBody UserProfileImpl userProfile){
        userDetailsService.updateAuthorities(userProfile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/user/activate")
    public ResponseEntity activateUser(@RequestParam("token") String token){
        System.out.println(token);
        return new ResponseEntity ( HttpStatus.OK);
    }

    @GetMapping(value="/users/profile/{username}")
    public ResponseEntity userProfile(@PathVariable String username){
        User user = userDetailsService.loadUserByUsername(username);
        if (user == null){
            return new ResponseEntity (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (user, HttpStatus.OK);
    }


}
