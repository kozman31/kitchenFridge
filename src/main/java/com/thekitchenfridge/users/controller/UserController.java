package com.thekitchenfridge.users.controller;

import com.thekitchenfridge.users.entity.User;
import com.thekitchenfridge.users.entity.UserProfileImpl;
import com.thekitchenfridge.users.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping(value="/admin/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> signup(@RequestBody UserProfileImpl userProfile){
        userDetailsService.registerNewUser(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value="/auth/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> updateUserRoles(@RequestBody UserProfileImpl userProfile){
        userDetailsService.updateAuthorities(userProfile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value="/auth/{user}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userProfile(@PathVariable String username){
        User user = userDetailsService.loadUserByUsername(username);
        if (user == null){
            return new ResponseEntity (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (user, HttpStatus.OK);
    }
}
