package com.thekitchenfridge.security.controller;

import com.thekitchenfridge.security.entity.User;
import com.thekitchenfridge.security.entity.UserProfile;
import com.thekitchenfridge.security.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping(value="/auth/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> signup(@RequestBody UserProfile userProfile){
        userDetailsService.registerNewUser(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value="/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void userProfile(@AuthenticationPrincipal Principal principal){
        log.info(principal.getName());
    }

    @RequestMapping(value="/auth/{user}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userProfile(@PathVariable String username){
        User user = userDetailsService.loadUserByUsername(username);
        if (user == null){
            return new ResponseEntity (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (user, HttpStatus.OK);
    }
}
